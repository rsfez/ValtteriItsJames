@file:OptIn(ExperimentalPermissionsApi::class)

package com.robined.valtteriitsjames.ui.teamradio

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.robined.valtteriitsjames.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File
import kotlin.coroutines.resume

@Composable
internal fun ImageShare(
    shareRequested: MutableState<Boolean>,
    graphicsLayer: GraphicsLayer,
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current
    val sharedPreferences =
        remember { context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE) }
    val haptics = LocalHapticFeedback.current
    val writeStorageAccessState = rememberMultiplePermissionsState(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            emptyList()
        } else {
            listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    )
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            getTeamRadioFile().delete()
            shareRequested.value = false
        }

    LaunchedEffect(Unit) {
        val hasSeenShareHint = sharedPreferences.getBoolean(SHARED_PREFERENCE_SHARE_HINT_KEY, false)
        if (!hasSeenShareHint) {
            snackbarHostState.showSnackbar(
                message = context.getString(R.string.share_hint_snackbar_message)
            )
            sharedPreferences.edit().putBoolean(SHARED_PREFERENCE_SHARE_HINT_KEY, true).apply()
        }
    }

    LaunchedEffect(shareRequested.value) {
        if (!shareRequested.value) return@LaunchedEffect

        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
        shareBitmapFromComposable(
            writeStorageAccessState = writeStorageAccessState,
            context = context,
            snackbarHostState = snackbarHostState,
            graphicsLayer = graphicsLayer,
            launcher = launcher
        )
    }
}

private fun CoroutineScope.shareBitmapFromComposable(
    writeStorageAccessState: MultiplePermissionsState,
    context: Context,
    snackbarHostState: SnackbarHostState,
    graphicsLayer: GraphicsLayer,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
) {
    if (writeStorageAccessState.allPermissionsGranted) {
        launch {
            val bitmap = graphicsLayer.toImageBitmap()
            val uri = bitmap.asAndroidBitmap().saveToDisk(context)
            shareBitmap(uri, launcher)
        }
    } else if (writeStorageAccessState.shouldShowRationale) {
        launch {
            val result = snackbarHostState.showSnackbar(
                message = context.getString(R.string.storage_permission_snackbar_message),
                actionLabel = context.getString(R.string.storage_permission_snackbar_action_label)
            )

            if (result == SnackbarResult.ActionPerformed) {
                writeStorageAccessState.launchMultiplePermissionRequest()
            }
        }
    } else {
        writeStorageAccessState.launchMultiplePermissionRequest()
    }
}

private suspend fun Bitmap.saveToDisk(context: Context): Uri {
    val file = getTeamRadioFile()

    file.writeBitmap(this, Bitmap.CompressFormat.PNG, 100)

    return scanFilePath(context, file.path) ?: throw Exception("File could not be saved")
}

private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
}

private suspend fun scanFilePath(context: Context, filePath: String): Uri? {
    return suspendCancellableCoroutine { continuation ->
        MediaScannerConnection.scanFile(
            context,
            arrayOf(filePath),
            arrayOf(PNG_MIME_TYPE)
        ) { _, scannedUri ->
            if (scannedUri == null) {
                continuation.cancel(Exception("File $filePath could not be scanned"))
            } else {
                continuation.resume(scannedUri)
            }
        }
    }
}

private fun shareBitmap(
    uri: Uri,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = PNG_MIME_TYPE
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    launcher.launch(createChooser(intent, SHARE_INTENT))
}

private fun getTeamRadioFile() = File(
    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
    IMAGE_FILENAME
)

private const val IMAGE_FILENAME = "teamRadio.png"
private const val SHARE_INTENT = "Share"
private const val SHARED_PREFERENCE_SHARE_HINT_KEY = "has_seen_share_hint"
private const val PNG_MIME_TYPE = "image/png"