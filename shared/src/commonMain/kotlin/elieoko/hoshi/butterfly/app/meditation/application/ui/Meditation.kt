package elieoko.hoshi.butterfly.app.meditation.application.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.pray
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.GlassCard
import elieoko.hoshi.butterfly.core.ui.components.PinCard
import elieoko.hoshi.butterfly.core.ui.components.PillRow
import elieoko.hoshi.butterfly.core.ui.components.SectionLabel
import elieoko.hoshi.butterfly.core.ui.components.butterflyImageFor
import elieoko.hoshi.butterfly.core.ui.feedback.LocalButterflyFeedback
import elieoko.hoshi.butterfly.design.ButterflyColors

@Composable
fun Meditation() {
    val feedback = LocalButterflyFeedback.current
    var progress by remember { mutableStateOf(0.28f) }
    val sessions = listOf(
        Triple("Silence du matin", "3 minutes • respiration + Psaume 23", 190.dp),
        Triple("Paix intérieure", "8 minutes • Philippiens 4", 160.dp),
        Triple("Présence de Dieu", "12 minutes • Jean 15", 210.dp),
    )

    ButterflyPage(
        title = "Méditation",
        subtitle = "Des sessions courtes pour ralentir, prier et écouter.",
        background = Res.drawable.pray,
    ) {
        GlassCard {
            SectionLabel("Session en cours")
            Text("Silence du matin", color = Color.White)
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                color = ButterflyColors.SoftGold,
                trackColor = Color.White.copy(alpha = 0.18f),
            )
            Button(
                onClick = {
                    progress = (progress + 0.18f).coerceAtMost(1f)
                    if (progress >= 1f) {
                        feedback.notify("Méditation terminée. Paix sur ta journée.")
                        progress = 0.1f
                    } else {
                        feedback.toast("Session reprise")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(if (progress < 1f) "Continuer" else "Recommencer")
            }
        }

        PillRow(
            labels = listOf("3 min", "5 min", "8 min", "12 min", "Soir"),
            onSelected = { feedback.toast("Durée $it sélectionnée") },
        )

        SectionLabel("Collections")
        sessions.forEachIndexed { index, (title, subtitle, height) ->
            PinCard(
                title = title,
                subtitle = subtitle,
                height = height,
                image = butterflyImageFor(index + 1),
                badge = "Méditer",
                onClick = { feedback.notify("Méditation « $title » démarrée") },
            )
        }
    }
}
