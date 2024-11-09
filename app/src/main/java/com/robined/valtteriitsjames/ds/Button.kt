package com.robined.valtteriitsjames.ds

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.robined.valtteriitsjames.ds.Spacing.xSmall
import com.robined.valtteriitsjames.ds.theme.AppTheme

@Composable
internal fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) = Button(
    modifier = modifier,
    onClick = onClick,
    enabled = enabled,
    content = content,
    shape = RoundedCornerShape(size = xSmall),
)

@Composable
@Preview
fun PrimaryButtonPreview() {
    AppTheme(dynamicColor = false) {
        PrimaryButton(onClick = {}) {
            Text(text = "Primary Button")
        }
    }
}