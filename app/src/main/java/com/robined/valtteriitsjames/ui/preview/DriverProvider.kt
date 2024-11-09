package com.robined.valtteriitsjames.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.robined.valtteriitsjames.domain.Driver
import com.robined.valtteriitsjames.domain.DriverPreset

internal class DriverProvider : PreviewParameterProvider<Driver> {
    override val values: Sequence<Driver> =
        DriverPreset.entries.map(DriverPreset::driver).asSequence()
}