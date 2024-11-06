package com.robined.valtteriitsjames.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.robined.valtteriitsjames.domain.Team

class TeamProvider : PreviewParameterProvider<Team> {
    override val values: Sequence<Team> = Team.entries.asSequence()
}