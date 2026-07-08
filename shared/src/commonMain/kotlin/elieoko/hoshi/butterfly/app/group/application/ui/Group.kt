package elieoko.hoshi.butterfly.app.group.application.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
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
import butterfly.shared.generated.resources.couple
import elieoko.hoshi.butterfly.core.session.LocalButterflySession
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.GlassCard
import elieoko.hoshi.butterfly.core.ui.components.PinCard
import elieoko.hoshi.butterfly.core.ui.components.SectionLabel
import elieoko.hoshi.butterfly.core.ui.components.butterflyImageFor
import elieoko.hoshi.butterfly.core.ui.feedback.LocalButterflyFeedback
import elieoko.hoshi.butterfly.design.ButterflyColors

private data class ShareGroup(
    val id: String,
    val name: String,
    val description: String,
    val inviteCode: String,
)

@Composable
fun Group() {
    val session = LocalButterflySession.current
    val feedback = LocalButterflyFeedback.current
    var inviteCode by remember { mutableStateOf("") }
    val groups = remember {
        listOf(
            ShareGroup("couple-hope", "Couples d'espérance", "Prière en duo et progression commune.", "HOPE-2026"),
            ShareGroup("young-adults", "Jeunes adultes en foi", "Partage quotidien et étude biblique.", "YOUNG-FAITH"),
            ShareGroup("family-altars", "Autels de famille", "Parents, enfants et dévotion en maison.", "FAMILY-ALTAR"),
        )
    }

    ButterflyPage(
        title = "Groupes",
        subtitle = "Rejoins un groupe de partage et lie ton compte à une communauté.",
        background = Res.drawable.couple,
    ) {
        GlassCard {
            SectionLabel("Rejoindre avec un code")
            OutlinedTextField(
                value = inviteCode,
                onValueChange = { inviteCode = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Ex: HOPE-2026") },
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
                    val match = groups.firstOrNull {
                        it.inviteCode.equals(inviteCode.trim(), ignoreCase = true)
                    }
                    when {
                        !session.isAuthenticated -> feedback.notify(
                            message = "Crée un compte avant de lier un groupe.",
                            actionLabel = "Compte",
                        )
                        match == null -> feedback.toast("Code invalide.")
                        session.isMemberOf(match.id) -> feedback.toast("Tu es déjà membre de ${match.name}.")
                        else -> {
                            session.joinGroup(match.id)
                            inviteCode = ""
                            feedback.notify("Compte lié au groupe « ${match.name} ».")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Lier mon compte au groupe")
            }
        }

        SectionLabel("Communautés ouvertes")
        groups.forEachIndexed { index, group ->
            val joined = session.isMemberOf(group.id)
            PinCard(
                title = group.name,
                subtitle = group.description + " • Code ${group.inviteCode}",
                height = if (index % 2 == 0) 190.dp else 170.dp,
                image = butterflyImageFor(index),
                badge = if (joined) "Membre" else "Rejoindre",
                onClick = {
                    when {
                        !session.isAuthenticated -> feedback.notify(
                            message = "Connecte ton compte pour rejoindre ${group.name}.",
                            actionLabel = "Compte",
                        )
                        joined -> feedback.toast("Déjà membre de ${group.name}")
                        else -> {
                            session.joinGroup(group.id)
                            feedback.notify("Bienvenue dans ${group.name}")
                        }
                    }
                },
            )
        }
    }
}
