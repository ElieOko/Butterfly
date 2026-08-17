package elieoko.hoshi.butterfly.app.onboarding.application.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elieoko.hoshi.butterfly.core.ui.components.AuroraOverlay
import elieoko.hoshi.butterfly.core.ui.components.ButterflySpacing
import elieoko.hoshi.butterfly.core.ui.components.ImmersiveBackground
import elieoko.hoshi.butterfly.core.ui.components.SacredOrnament
import elieoko.hoshi.butterfly.core.ui.components.SacredPrimaryButton
import elieoko.hoshi.butterfly.design.ButterflyColors
import elieoko.hoshi.butterfly.design.SpiritualImagery

private data class OnboardingStep(
    val title: String,
    val subtitle: String,
    val imageUrl: String,
)

private val onboardingSteps = listOf(
    OnboardingStep(
        title = "Un sanctuaire numérique",
        subtitle = "Lire, noter, méditer et grandir — avec élégance et simplicité.",
        imageUrl = SpiritualImagery.natureSpirit,
    ),
    OnboardingStep(
        title = "Bible, notes & méditation",
        subtitle = "Des espaces immersifs pour ta discipline quotidienne.",
        imageUrl = SpiritualImagery.bibleOpen,
    ),
    OnboardingStep(
        title = "Communauté & compte",
        subtitle = "Rejoins un groupe et partage ta progression spirituelle.",
        imageUrl = SpiritualImagery.coupleFaith,
    ),
)

@Composable
fun Onboarding(onFinish: () -> Unit) {
    var stepIndex by remember { mutableStateOf(0) }
    val step = onboardingSteps[stepIndex]
    val isLast = stepIndex == onboardingSteps.lastIndex

    Box(modifier = Modifier.fillMaxSize()) {
        ImmersiveBackground(imageUrl = step.imageUrl, dimAlpha = 0.50f)
        AuroraOverlay(modifier = Modifier.fillMaxSize())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(ButterflySpacing.xl),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "BUTTERFLY",
                style = MaterialTheme.typography.labelSmall,
                color = ButterflyColors.SoftGold,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
            )

            Crossfade(targetState = step, label = "onboarding") { current ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(ButterflySpacing.lg),
                ) {
                    Text(
                        text = current.title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Light,
                        ),
                        color = ButterflyColors.WarmCream,
                    )
                    SacredOrnament()
                    Text(
                        text = current.subtitle,
                        style = MaterialTheme.typography.bodyLarge,
                        color = ButterflyColors.MistMuted,
                        modifier = Modifier.padding(horizontal = ButterflySpacing.md),
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(ButterflySpacing.lg),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    repeat(onboardingSteps.size) { index ->
                        val active = index == stepIndex
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .size(if (active) 10.dp else 7.dp)
                                .clip(CircleShape)
                                .then(
                                    if (active) {
                                        Modifier.background(ButterflyColors.SoftGold)
                                    } else {
                                        Modifier.background(Color.White.copy(alpha = 0.22f))
                                    },
                                ),
                        )
                    }
                }
                SacredPrimaryButton(
                    text = if (isLast) "Entrer dans Butterfly" else "Continuer",
                    onClick = {
                        if (isLast) onFinish() else stepIndex += 1
                    },
                )
            }
        }
    }
}
