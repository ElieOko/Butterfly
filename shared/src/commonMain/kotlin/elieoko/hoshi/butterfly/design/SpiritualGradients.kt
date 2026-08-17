package elieoko.hoshi.butterfly.design

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object SpiritualGradients {
    val auroraVeil: Brush = Brush.verticalGradient(
        listOf(
            ButterflyColors.AuroraIndigo.copy(alpha = 0.55f),
            ButterflyColors.Night.copy(alpha = 0.35f),
            ButterflyColors.Night.copy(alpha = 0.88f),
            ButterflyColors.Night.copy(alpha = 0.97f),
        ),
    )

    val auroraHorizontal: Brush = Brush.linearGradient(
        listOf(
            ButterflyColors.SoftViolet.copy(alpha = 0.22f),
            Color.Transparent,
            ButterflyColors.SoftBlue.copy(alpha = 0.18f),
            Color.Transparent,
            ButterflyColors.RoseDawn.copy(alpha = 0.14f),
        ),
        start = Offset(0f, 0f),
        end = Offset(1200f, 400f),
    )

    val sacredBorder: Brush = Brush.linearGradient(
        listOf(
            ButterflyColors.SoftGold.copy(alpha = 0.65f),
            ButterflyColors.SoftViolet.copy(alpha = 0.45f),
            ButterflyColors.SoftBlue.copy(alpha = 0.55f),
        ),
    )

    val goldShimmer: Brush = Brush.linearGradient(
        listOf(
            ButterflyColors.SoftGold.copy(alpha = 0.85f),
            ButterflyColors.WarmCream,
            ButterflyColors.SoftGold.copy(alpha = 0.7f),
        ),
    )

    val glassSurface: Brush = Brush.verticalGradient(
        listOf(
            Color.White.copy(alpha = 0.10f),
            Color.White.copy(alpha = 0.04f),
        ),
    )

    val ctaButton: Brush = Brush.linearGradient(
        listOf(
            ButterflyColors.SoftGold,
            ButterflyColors.RoseDawn,
        ),
    )

    val navBarFill: Brush = Brush.linearGradient(
        listOf(
            ButterflyColors.NightCard.copy(alpha = 0.94f),
            ButterflyColors.Midnight.copy(alpha = 0.98f),
        ),
    )
}
