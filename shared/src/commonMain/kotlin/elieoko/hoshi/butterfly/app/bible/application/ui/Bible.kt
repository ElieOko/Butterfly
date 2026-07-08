package elieoko.hoshi.butterfly.app.bible.application.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.hand
import butterfly.shared.generated.resources.pray
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.GlassCard
import elieoko.hoshi.butterfly.core.ui.components.PinCard
import elieoko.hoshi.butterfly.core.ui.components.PillRow
import elieoko.hoshi.butterfly.core.ui.components.SectionLabel
import elieoko.hoshi.butterfly.core.ui.feedback.LocalButterflyFeedback
import elieoko.hoshi.butterfly.design.ButterflyColors

@Composable
fun Bible() {
    val feedback = LocalButterflyFeedback.current
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    ButterflyPage(
        title = "Bible",
        subtitle = "Lecture claire, recherche rapide, surlignage et partage.",
        background = Res.drawable.hand,
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Rechercher un thème, un livre, un verset...") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = ButterflyColors.SoftBlue,
                unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
                cursorColor = ButterflyColors.SoftBlue,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
        )

        PillRow(
            labels = listOf("Audio", "Surligner", "Favoris", "Partager", "Historique"),
            onSelected = { feedback.toast("$it activé") },
        )

        PinCard(
            title = "Jean 14:27",
            subtitle = "Je vous laisse la paix, je vous donne ma paix...",
            height = 200.dp,
            image = Res.drawable.pray,
            badge = "Lecture",
            onClick = {
                expanded = !expanded
                feedback.toast(if (expanded) "Explication ouverte" else "Explication fermée")
            },
        )

        AnimatedVisibility(visible = expanded) {
            GlassCard {
                SectionLabel("Comprendre le passage")
                Text(
                    text = "Jésus offre une paix durable, indépendante des circonstances. " +
                        "Lis aussi Philippiens 4:6-7 pour approfondir.",
                    color = Color.White.copy(alpha = 0.9f),
                )
            }
        }

        PinCard(
            title = "Philippiens 4:6-7",
            subtitle = "Ne vous inquiétez de rien; présentez vos besoins à Dieu...",
            height = 180.dp,
            image = Res.drawable.hand,
            badge = "Espérance",
            onClick = { feedback.notify("Passage ajouté aux favoris") },
        )

        PinCard(
            title = "Psaume 23",
            subtitle = "L'Éternel est mon berger: je ne manquerai de rien.",
            height = 160.dp,
            image = Res.drawable.pray,
            badge = "Consolation",
            onClick = { feedback.toast("Lecture démarrée") },
        )
    }
}
