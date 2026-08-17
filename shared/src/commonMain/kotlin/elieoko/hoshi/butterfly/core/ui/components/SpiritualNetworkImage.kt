package elieoko.hoshi.butterfly.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import elieoko.hoshi.butterfly.design.ButterflyColors

@Composable
fun SpiritualNetworkImage(
  url: String,
  modifier: Modifier = Modifier,
  contentScale: ContentScale = ContentScale.Crop,
) {
  SubcomposeAsyncImage(
    model = url,
    contentDescription = null,
    modifier = modifier,
    contentScale = contentScale,
    loading = {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.linearGradient(
              listOf(
                ButterflyColors.Midnight,
                ButterflyColors.NightCard,
                ButterflyColors.NightElevated,
              ),
            ),
          ),
      )
    },
    error = {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              listOf(
                ButterflyColors.SoftViolet.copy(alpha = 0.35f),
                ButterflyColors.NightCard,
                ButterflyColors.Night,
              ),
            ),
          ),
      )
    },
  )
}
