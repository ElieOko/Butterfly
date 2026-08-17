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

/** Editorial spiritual palette — velvety night with aurora accents. */
object ButterflyColors {
    val Night = Color(0xFF050508)
    val NightElevated = Color(0xFF0E0E12)
    val NightCard = Color(0xFF16161C)
    val Midnight = Color(0xFF1C1D26)
    val AuroraIndigo = Color(0xFF2A2D4A)
    val SoftBlue = Color(0xFF8BE8FF)
    val SoftViolet = Color(0xFFD4C4FF)
    val SoftGold = Color(0xFFE8C97A)
    val RoseDawn = Color(0xFFE8A4B8)
    val WarmCream = Color(0xFFF8F2E8)
    val Mist = Color(0xFFF2F2F2)
    val MistMuted = Color(0xFF9A9AA8)
    val Glass = Color(0xE814141A)
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
    outline = Color(0xFF3B3B40),
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
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Light,
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.3).sp,
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
