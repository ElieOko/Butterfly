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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.bible
import butterfly.shared.generated.resources.butterfly
import butterfly.shared.generated.resources.couple
import elieoko.hoshi.butterfly.core.ui.components.ButterflySpacing
import elieoko.hoshi.butterfly.core.ui.components.GlassCard
import elieoko.hoshi.butterfly.core.ui.components.ImmersiveBackground
import elieoko.hoshi.butterfly.design.ButterflyColors
import org.jetbrains.compose.resources.DrawableResource

private data class OnboardingStep(
    val title: String,
    val subtitle: String,
    val image: DrawableResource,
)

private val onboardingSteps = listOf(
    OnboardingStep(
        title = "Bienvenue sur Butterfly",
        subtitle = "Une expérience spirituelle moderne pour lire, noter, méditer et grandir.",
        image = Res.drawable.butterfly,
    ),
    OnboardingStep(
        title = "Bible, notes & méditation",
        subtitle = "Des espaces clairs, immersifs et cohérents pour ta discipline quotidienne.",
        image = Res.drawable.bible,
    ),
    OnboardingStep(
        title = "Communauté & compte",
        subtitle = "Crée ton compte, rejoins un groupe et partage ta progression.",
        image = Res.drawable.couple,
    ),
)

@Composable
fun Onboarding(onFinish: () -> Unit) {
    var stepIndex by remember { mutableStateOf(0) }
    val step = onboardingSteps[stepIndex]
    val isLast = stepIndex == onboardingSteps.lastIndex

    Box(modifier = Modifier.fillMaxSize()) {
        ImmersiveBackground(image = step.image, dimAlpha = 0.55f)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(ButterflySpacing.xl),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "BUTTERFLY",
                style = MaterialTheme.typography.labelLarge,
                color = ButterflyColors.SoftGold,
                fontWeight = FontWeight.Bold,
            )

            GlassCard {
                Crossfade(targetState = step, label = "onboarding") { current ->
                    Column(verticalArrangement = Arrangement.spacedBy(ButterflySpacing.md)) {
                        Text(
                            text = current.title,
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = current.subtitle,
                            style = MaterialTheme.typography.bodyLarge,
                            color = ButterflyColors.MistMuted,
                        )
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(ButterflySpacing.lg)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    repeat(onboardingSteps.size) { index ->
                        val active = index == stepIndex
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(if (active) 10.dp else 8.dp)
                                .background(
                                    color = if (active) ButterflyColors.SoftBlue else Color.White.copy(alpha = 0.28f),
                                    shape = RoundedCornerShape(999.dp),
                                ),
                        )
                    }
                }
                Button(
                    onClick = {
                        if (isLast) onFinish() else stepIndex += 1
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(if (isLast) "Entrer dans Butterfly" else "Continuer")
                }
            }
        }
    }
}
