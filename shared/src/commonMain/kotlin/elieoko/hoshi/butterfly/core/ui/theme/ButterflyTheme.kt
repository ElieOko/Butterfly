package elieoko.hoshi.butterfly.core.ui.theme

import androidx.compose.runtime.Composable
import elieoko.hoshi.butterfly.design.SpiritualTheme

@Composable
fun ButterflyTheme(content: @Composable () -> Unit) {
    SpiritualTheme {
        content()
    }
}
