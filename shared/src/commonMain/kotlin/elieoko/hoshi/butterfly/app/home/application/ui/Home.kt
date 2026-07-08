package elieoko.hoshi.butterfly.app.home.application.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.couple
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.OrganicCard
import elieoko.hoshi.butterfly.core.ui.components.PillRow
import org.jetbrains.compose.resources.painterResource

@Composable
fun Home(
    onOpenGroups: () -> Unit,
    onOpenAssistant: () -> Unit,
    onOpenAbout: () -> Unit,
) {
    ButterflyPage(
        title = "Bonjour, bienvenue",
        subtitle = "Un parcours uniforme et elegant pour la lecture, la priere et la communaute.",
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            HomeMetric("Serie", "21 j", modifier = Modifier.weight(1f))
            HomeMetric("Plans", "4 actifs", modifier = Modifier.weight(1f))
            HomeMetric("Groupes", "2", modifier = Modifier.weight(1f))
        }

        OrganicCard(
            title = "Verset du jour",
            subtitle = "\"Ta parole est une lampe a mes pieds, et une lumiere sur mon sentier.\"",
        ) {
            Text("Psaume 119:105", color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.92f))
            LinearProgressIndicator(
                progress = { 0.74f },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small),
                color = MaterialTheme.colorScheme.tertiary,
                trackColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.25f),
            )
        }

        OrganicCard(
            title = "Actions rapides",
            subtitle = "Accede aux espaces essentiels de Butterfly.",
            accentA = MaterialTheme.colorScheme.secondary.copy(alpha = 0.86f),
            accentB = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(onClick = onOpenGroups, modifier = Modifier.weight(1f)) { Text("Groupes") }
                Button(onClick = onOpenAssistant, modifier = Modifier.weight(1f)) { Text("Assistant") }
            }
            Button(onClick = onOpenAbout, modifier = Modifier.fillMaxWidth()) { Text("A propos") }
        }

        OrganicCard(
            title = "Communaute active",
            subtitle = "Etude en couple, priere de groupe et encouragements quotidiens.",
            accentA = Color(0xFF2B3F86),
            accentB = Color(0xFF6C4FAF),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(Res.drawable.couple),
                    contentDescription = "Etude en couple",
                    modifier = Modifier.size(108.dp),
                )
            }
        }

        PillRow(
            labels = listOf(
                "Meditation du matin",
                "Priere du soir",
                "Lecture en couple",
                "Defi hebdo",
            ),
        )
    }
}

@Composable
private fun HomeMetric(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.height(72.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.88f)),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.titleMedium)
        }
    }
}
