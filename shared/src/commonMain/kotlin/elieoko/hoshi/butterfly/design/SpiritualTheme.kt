package elieoko.hoshi.butterfly.design

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Night-blue brand palette for Butterfly. */
object ButterflyColors {
    val Night = Color(0xFF070B18)
    val NightElevated = Color(0xFF10182B)
    val NightCard = Color(0xFF162038)
    val Midnight = Color(0xFF1A2744)
    val SoftBlue = Color(0xFF7EA2FF)
    val SoftViolet = Color(0xFFB39DFF)
    val SoftGold = Color(0xFFE2C56A)
    val Mist = Color(0xFFD7DEF5)
    val MistMuted = Color(0xFF9AA6C7)
    val Glass = Color(0xCC121A2E)
}

private val NightScheme: ColorScheme = darkColorScheme(
    primary = ButterflyColors.SoftBlue,
    secondary = ButterflyColors.SoftViolet,
    tertiary = ButterflyColors.SoftGold,
    background = ButterflyColors.Night,
    surface = ButterflyColors.NightElevated,
    surfaceVariant = ButterflyColors.NightCard,
    onPrimary = ButterflyColors.Night,
    onSecondary = ButterflyColors.Night,
    onTertiary = ButterflyColors.Night,
    onBackground = ButterflyColors.Mist,
    onSurface = ButterflyColors.Mist,
    onSurfaceVariant = ButterflyColors.MistMuted,
    outline = Color(0xFF3A4666),
)

private val SpiritualTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.4).sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.2).sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 18.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.sp,
    ),
)

private val SpiritualShapes = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(36.dp),
)

@Composable
fun SpiritualTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = NightScheme,
        typography = SpiritualTypography,
        shapes = SpiritualShapes,
        content = content,
    )
}
