package elieoko.hoshi.butterfly.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DeepBlue = Color(0xFF1E2A78)
private val Gold = Color(0xFFC9A227)
private val Violet = Color(0xFF6E4AB5)
private val WarmWhite = Color(0xFFFAFAFD)
private val SoftSurface = Color(0xFFF2F4FB)
private val Ink = Color(0xFF121212)
private val InkMuted = Color(0xFF5F6368)

private val LightColors: ColorScheme = lightColorScheme(
    primary = DeepBlue,
    secondary = Violet,
    tertiary = Gold,
    background = WarmWhite,
    surface = Color.White,
    surfaceVariant = SoftSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Ink,
    onBackground = Ink,
    onSurface = Ink,
    onSurfaceVariant = InkMuted,
)

private val DarkColors: ColorScheme = darkColorScheme(
    primary = Color(0xFFA9B3FF),
    secondary = Color(0xFFC8B1FF),
    tertiary = Color(0xFFF2D580),
    background = Color(0xFF0F1220),
    surface = Color(0xFF181C2F),
    surfaceVariant = Color(0xFF252A3F),
    onPrimary = Color(0xFF0F1220),
    onSecondary = Color(0xFF140F1E),
    onTertiary = Color(0xFF251F08),
    onBackground = Color(0xFFE8EAF8),
    onSurface = Color(0xFFE8EAF8),
    onSurfaceVariant = Color(0xFFC7CCE3),
)

private val SpiritualTypography = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 36.sp,
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
        fontSize = 18.sp,
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
        lineHeight = 19.sp,
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
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(22.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(30.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(40.dp),
)

@Composable
fun SpiritualTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = SpiritualTypography,
        shapes = SpiritualShapes,
        content = content,
    )
}
