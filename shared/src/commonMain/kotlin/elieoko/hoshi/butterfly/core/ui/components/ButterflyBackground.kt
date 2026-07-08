package elieoko.hoshi.butterfly.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.butterfly

/** Kept for compatibility; immersive backgrounds are preferred. */
@Composable
fun ButterflyBackground(modifier: Modifier = Modifier) {
    ImmersiveBackground(image = Res.drawable.butterfly, modifier = modifier)
}
