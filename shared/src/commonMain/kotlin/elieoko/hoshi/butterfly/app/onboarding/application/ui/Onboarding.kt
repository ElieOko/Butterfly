package elieoko.hoshi.butterfly.app.onboarding.application.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.butterfly
import elieoko.hoshi.butterfly.core.ui.components.ButterflyBackground
import org.jetbrains.compose.resources.painterResource

private data class OnboardingStep(
    val title: String,
    val subtitle: String,
)

private val onboardingSteps = listOf(
    OnboardingStep(
        title = "Bienvenue sur Butterfly",
        subtitle = "Une application moderne pour grandir dans la Parole, la priere et la communion.",
    ),
    OnboardingStep(
        title = "Parcours personnalise",
        subtitle = "Plans, meditation, versets et accompagnement adaptes a ta saison de vie.",
    ),
    OnboardingStep(
        title = "Vivre la foi ensemble",
        subtitle = "Rejoins des groupes de partage, prie a plusieurs et celebre chaque progression.",
    ),
)

@Composable
fun Onboarding(
    onFinish: () -> Unit,
) {
    var stepIndex by remember { mutableStateOf(0) }
    val isLastStep = stepIndex == onboardingSteps.lastIndex

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.butterfly),
            contentDescription = "Image de fond Butterfly",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.62f)),
        )
        ButterflyBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 34.dp, topEnd = 14.dp, bottomStart = 14.dp, bottomEnd = 34.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f),
                                    ),
                                ),
                                shape = RoundedCornerShape(22.dp),
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.butterfly),
                            contentDescription = "Butterfly illustration",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("BUTTERFLY", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(
                        "Croissance spirituelle intelligente",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.92f),
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.92f),
                                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.86f),
                                ),
                            ),
                        )
                        .padding(18.dp),
                ) {
                    Crossfade(targetState = onboardingSteps[stepIndex], label = "onboarding-step") { step ->
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(step.title, color = Color.White, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                            Text(step.subtitle, color = Color.White.copy(alpha = 0.9f), style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    repeat(onboardingSteps.size) { index ->
                        val color = if (index == stepIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(if (index == stepIndex) 12.dp else 8.dp)
                                .background(color = color, shape = RoundedCornerShape(999.dp)),
                        )
                    }
                }
                Button(
                    onClick = {
                        if (isLastStep) onFinish() else stepIndex += 1
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(if (isLastStep) "Entrer dans Butterfly" else "Continuer")
                }
            }
        }
    }
}
