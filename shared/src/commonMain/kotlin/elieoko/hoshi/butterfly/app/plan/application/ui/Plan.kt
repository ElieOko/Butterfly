package elieoko.hoshi.butterfly.app.plan.application.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.ButterflyBadge
import elieoko.hoshi.butterfly.core.ui.components.OrganicCard
import elieoko.hoshi.butterfly.core.ui.components.PillRow

@Composable
fun Plan() {
    val plans = listOf(
        "21 jours - Couple et priere" to 0.62f,
        "14 jours - Guerison interieure" to 0.35f,
        "30 jours - Discipline spirituelle" to 0.14f,
    )

    ButterflyPage(
        title = "Plans de lecture",
        subtitle = "Programmes organises par profil: couple, famille, celibataire, jeunesse.",
    ) {
        PillRow(
            labels = listOf(
                "Couple",
                "Mariage",
                "Famille",
                "Jeunes",
                "Leadership",
                "Priere",
            ),
        )

        plans.forEachIndexed { index, (title, progress) ->
            OrganicCard(
                title = title,
                subtitle = "Lecture, meditation, questions, priere et defi quotidien.",
                accentA = if (index % 2 == 0) Color(0xFF2E458E) else Color(0xFF6A4DAF),
                accentB = if (index % 2 == 0) Color(0xFF4E66C8) else Color(0xFF8A68C2),
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    ButterflyBadge("Jour ${(progress * 100).toInt() / 5} / 20")
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.small),
                        color = MaterialTheme.colorScheme.tertiary,
                        trackColor = Color.White.copy(alpha = 0.2f),
                    )
                    Text(
                        text = "${(progress * 100).toInt()}% complete",
                        color = Color.White.copy(alpha = 0.9f),
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }
}
