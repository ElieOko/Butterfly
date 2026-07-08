package elieoko.hoshi.butterfly.app.note.application.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.couple
import butterfly.shared.generated.resources.pray
import elieoko.hoshi.butterfly.core.session.LocalButterflySession
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.GlassCard
import elieoko.hoshi.butterfly.core.ui.components.PinCard
import elieoko.hoshi.butterfly.core.ui.components.SectionLabel
import elieoko.hoshi.butterfly.core.ui.components.butterflyImageFor
import elieoko.hoshi.butterfly.core.ui.feedback.LocalButterflyFeedback
import elieoko.hoshi.butterfly.design.ButterflyColors

@Composable
fun Notes() {
    val session = LocalButterflySession.current
    val feedback = LocalButterflyFeedback.current
    var draft by remember { mutableStateOf("") }
    val notes = remember {
        mutableStateListOf(
            "Gratitude du matin — Dieu a ouvert une porte inattendue.",
            "Réflexion Jean 15 — demeurer en Christ dans les décisions.",
            "Prière pour ma famille — paix et unité cette semaine.",
        )
    }

    ButterflyPage(
        title = "Notes",
        subtitle = "Capture tes révélations, prières et insights bibliques.",
        background = Res.drawable.couple,
    ) {
        GlassCard {
            SectionLabel("Nouvelle note")
            OutlinedTextField(
                value = draft,
                onValueChange = { draft = it },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                placeholder = { Text("Écris ce que Dieu te montre aujourd'hui...") },
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
            Button(
                onClick = {
                    when {
                        !session.isAuthenticated -> feedback.notify(
                            message = "Crée un compte pour sauvegarder tes notes.",
                            actionLabel = "Compte",
                        )
                        draft.isBlank() -> feedback.toast("Écris une note avant de sauvegarder.")
                        else -> {
                            notes.add(0, draft.trim())
                            draft = ""
                            feedback.toast("Note enregistrée")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Enregistrer la note")
            }
        }

        SectionLabel("Journal récent")
        notes.forEachIndexed { index, note ->
            PinCard(
                title = "Note ${notes.size - index}",
                subtitle = note,
                height = if (index % 2 == 0) 170.dp else 150.dp,
                image = butterflyImageFor(index),
                badge = if (index == 0) "Nouveau" else "Perso",
                onClick = { feedback.toast("Note ouverte") },
            )
        }

        PinCard(
            title = "Astuce",
            subtitle = "Relie chaque note à un verset pour retrouver le contexte plus tard.",
            height = 140.dp,
            image = Res.drawable.pray,
            badge = "Conseil",
        )
    }
}
