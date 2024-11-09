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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File
import kotlin.coroutines.resume

@Composable
internal fun ImageShare(shareRequested: Boolean, graphicsLayer: GraphicsLayer) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val haptics = LocalHapticFeedback.current
    val writeStorageAccessState = rememberMultiplePermissionsState(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            emptyList()
        } else {
            listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    )

    LaunchedEffect(shareRequested) {
        if (!shareRequested) return@LaunchedEffect

        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
        shareBitmapFromComposable(
            writeStorageAccessState = writeStorageAccessState,
            context = context,
            snackbarHostState = snackbarHostState,
            graphicsLayer = graphicsLayer
        )
    }
}

private fun CoroutineScope.shareBitmapFromComposable(
    writeStorageAccessState: MultiplePermissionsState,
    context: Context,
    snackbarHostState: SnackbarHostState,
    graphicsLayer: GraphicsLayer,
) {
    if (writeStorageAccessState.allPermissionsGranted) {
        launch {
            val bitmap = graphicsLayer.toImageBitmap()
            val uri = bitmap.asAndroidBitmap().saveToDisk(context)
            shareBitmap(context, uri)
        }
    } else if (writeStorageAccessState.shouldShowRationale) {
        launch {
            val result = snackbarHostState.showSnackbar(
                message = "The storage permission is needed to save the image",
                actionLabel = "Grant Access"
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
    val file = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
        "teamRadio.png"
    )

    file.writeBitmap(this, Bitmap.CompressFormat.PNG, 100)

    return scanFilePath(context, file.path) ?: throw Exception("File could not be saved")
}

private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
}

private fun shareBitmap(context: Context, uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "image/png"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(createChooser(intent, "Share"), null)
}

private suspend fun scanFilePath(context: Context, filePath: String): Uri? {
    return suspendCancellableCoroutine { continuation ->
        MediaScannerConnection.scanFile(
            context,
            arrayOf(filePath),
            arrayOf("image/png")
        ) { _, scannedUri ->
            if (scannedUri == null) {
                continuation.cancel(Exception("File $filePath could not be scanned"))
            } else {
                continuation.resume(scannedUri)
            }
        }
    }
}