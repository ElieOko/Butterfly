package elieoko.hoshi.butterfly

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import elieoko.hoshi.butterfly.core.ui.theme.ButterflyTheme
import elieoko.hoshi.butterfly.view.navigation.NavigationRoot

@Composable
@Preview
fun App() {
    ButterflyTheme {
        NavigationRoot()
    }
}