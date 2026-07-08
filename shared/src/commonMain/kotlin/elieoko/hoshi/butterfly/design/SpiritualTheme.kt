package elieoko.hoshi.butterfly.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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

@Composable
fun SpiritualTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = MaterialTheme.typography,
        content = content,
    )
}
