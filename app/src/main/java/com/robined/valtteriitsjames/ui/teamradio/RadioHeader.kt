package com.robined.valtteriitsjames.ui.teamradio

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.robined.valtteriitsjames.AutoSizeText
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.domain.Driver
import com.robined.valtteriitsjames.domain.Team
import com.robined.valtteriitsjames.ds.DimensInt.toDp
import com.robined.valtteriitsjames.ds.Font
import com.robined.valtteriitsjames.ds.Spacing.large
import com.robined.valtteriitsjames.ds.Spacing.medium
import com.robined.valtteriitsjames.ds.Spacing.xLarge
import com.robined.valtteriitsjames.ds.Spacing.xSmall
import com.robined.valtteriitsjames.ds.Spacing.xxLarge
import com.robined.valtteriitsjames.ds.Spacing.xxxLarge
import com.robined.valtteriitsjames.ds.theme.messageStyle
import com.robined.valtteriitsjames.ds.theme.primaryText
import com.robined.valtteriitsjames.ds.theme.transparent
import com.robined.valtteriitsjames.ds.theme.white_2
import com.robined.valtteriitsjames.ds.theme.white_5
import com.robined.valtteriitsjames.ui.preview.DriverProvider
import com.robined.valtteriitsjames.ui.preview.TeamProvider
import java.util.Locale
import kotlin.math.ln

@Composable
internal fun RadioHeader(driver: Driver) {
    val team = driver.team
    val colorStops = arrayOf(
        0.0f to white_5,
        0.2f to white_2,
        1f to transparent
    )
    Column(modifier = Modifier.background(Brush.verticalGradient(colorStops = colorStops))) {
        DriverAndTeamHeader(driver = driver)
        Box(modifier = Modifier.height(medium))
        AnimatedVerticalBars(team)
        Box(modifier = Modifier.height(medium))
        HorizontalBar(team)
    }
}

@Preview
@Composable
private fun RadioHeaderPreview(@PreviewParameter(DriverProvider::class) driver: Driver) {
    RadioHeader(driver = driver)
}

@Composable
private fun DriverAndTeamHeader(driver: Driver) {
    val team = driver.team
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = xLarge)
            .padding(top = large),
        horizontalAlignment = Alignment.End
    ) {
        var finalTextSize by remember { mutableStateOf(TextUnit.Unspecified) }
        AutoSizeText(
            text = driver.driverName(),
            maxLines = 1,
            softWrap = false,
            maxTextSize = Font.xxLarge,
            alignment = Alignment.CenterEnd,
            style = messageStyle,
            color = team.color,
            onTextLayout = { textLayoutResult ->
                finalTextSize = textLayoutResult.layoutInput.style.fontSize
            }
        )
        Row {
            var imageHeightPx by remember { mutableIntStateOf(0) }
            Image(
                painter = painterResource(team.logoResId),
                contentDescription = team.nameContentDescription,
                modifier = Modifier
                    .height(imageHeightPx.toDp())
                    .widthIn(min = 0.dp, max = xxxLarge)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Fit
            )
            Box(modifier = Modifier.width(medium))
            Text(
                text = stringResource(R.string.radio).uppercase(Locale.getDefault()),
                textAlign = TextAlign.End,
                style = messageStyle,
                fontSize = Font.xxLarge,
                color = primaryText,
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    imageHeightPx =
                        coordinates.size.height - coordinates.size.height * 10 / 100
                },
            )
        }
    }
}

@Preview
@Composable
private fun DriverAndTeamHeaderPreview(@PreviewParameter(DriverProvider::class) driver: Driver) {
    DriverAndTeamHeader(driver = driver)
}

@Composable
private fun AnimatedVerticalBars(team: Team) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(xxLarge)
            .padding(horizontal = large),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val nbBars = 33
        val barHeight = 24
        repeat(nbBars) { i ->
            val targetValue = if (i <= nbBars / 2) {
                barHeight * ln((i + 1).toDouble()) / ln((nbBars / 2 + 1).toDouble())
            } else {
                barHeight * ln((nbBars - i + 1).toDouble()) / ln((nbBars / 2 + 1).toDouble())
            }.toFloat()
            val infiniteTransition = rememberInfiniteTransition(label = "infinite")
            val animatedHeight by infiniteTransition.animateFloat(
                initialValue = targetValue - targetValue / 3,
                targetValue = targetValue,
                animationSpec = infiniteRepeatable(
                    animation = tween(333, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse,
                    initialStartOffset = StartOffset(
                        (i * Math.random() * 100).toInt(),
                        StartOffsetType.FastForward
                    )
                ),
                label = "radio"
            )

            Box(
                modifier = Modifier
                    .width(6.dp)
                    .height(animatedHeight.dp)
                    .background(color = team.color)
                    .shadow(elevation = large, spotColor = team.color)
            )
        }
    }
}

@Composable
@Preview
private fun AnimatedVerticalBarsPreview(@PreviewParameter(TeamProvider::class) team: Team) {
    AnimatedVerticalBars(team = team)
}

@Composable
private fun HorizontalBar(team: Team) {
    Box(
        modifier = Modifier
            .background(color = team.color)
            .height(xSmall)
            .fillMaxWidth()
    )
}

@Composable
@Preview
private fun HorizontalBarPreview(@PreviewParameter(TeamProvider::class) team: Team) {
    HorizontalBar(team = team)
}