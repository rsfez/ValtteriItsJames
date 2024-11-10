package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.robined.valtteriitsjames.ds.Spacing.medium
import com.robined.valtteriitsjames.ds.Spacing.small
import com.robined.valtteriitsjames.ds.Spacing.xLarge
import com.robined.valtteriitsjames.ds.Spacing.xxLarge

@Composable
internal fun BottomSheetSelectionRow(color: Color, text: String) {
    Row(
        modifier = Modifier.padding(horizontal = xxLarge, vertical = small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(small)
                .height(xLarge)
                .background(color)
        )
        Box(modifier = Modifier.width(medium))
        Text(text = text)
    }
}