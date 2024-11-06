package com.robined.valtteriitsjames.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.robined.valtteriitsjames.domain.Driver

class DriverProvider : PreviewParameterProvider<Driver> {
    override val values: Sequence<Driver> = Driver.entries.asSequence()
}