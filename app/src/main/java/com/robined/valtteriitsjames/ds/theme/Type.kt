package com.robined.valtteriitsjames.ds.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.robined.valtteriitsjames.R

private val typography = Typography()

private val f1Font = FontFamily(
    Font(R.font.formula1_bold, FontWeight.Bold),
    Font(R.font.formula1_regular, FontWeight.Normal),
    Font(R.font.formula1_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.formula1_wide, FontWeight.ExtraBold),
)

val AppTypography = Typography(
    displayLarge = typography.displayLarge.copy(fontFamily = f1Font),
    displayMedium = typography.displayMedium.copy(fontFamily = f1Font),
    displaySmall = typography.displaySmall.copy(fontFamily = f1Font),
    headlineLarge = typography.headlineLarge.copy(fontFamily = f1Font),
    headlineMedium = typography.headlineMedium.copy(fontFamily = f1Font),
    headlineSmall = typography.headlineSmall.copy(fontFamily = f1Font),
    titleLarge = typography.titleLarge.copy(fontFamily = f1Font),
    titleMedium = typography.titleMedium.copy(fontFamily = f1Font),
    titleSmall = typography.titleSmall.copy(fontFamily = f1Font),
    bodyLarge = typography.bodyLarge.copy(fontFamily = f1Font),
    bodyMedium = typography.bodyMedium.copy(fontFamily = f1Font),
    bodySmall = typography.bodySmall.copy(fontFamily = f1Font),
    labelLarge = typography.labelLarge.copy(fontFamily = f1Font),
    labelMedium = typography.labelMedium.copy(fontFamily = f1Font),
    labelSmall = typography.labelSmall.copy(fontFamily = f1Font)
)

val messageStyle = TextStyle(
    fontFamily = f1Font,
    fontStyle = FontStyle.Italic,
    fontWeight = FontWeight.Bold,
)

