package elieoko.hoshi.butterfly.app.auth.application.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.butterfly
import butterfly.shared.generated.resources.couple
import butterfly.shared.generated.resources.pray
import elieoko.hoshi.butterfly.core.session.LocalButterflySession
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.GlassCard
import elieoko.hoshi.butterfly.core.ui.components.PinCard
import elieoko.hoshi.butterfly.core.ui.components.SectionLabel
import elieoko.hoshi.butterfly.core.ui.feedback.LocalButterflyFeedback
import elieoko.hoshi.butterfly.design.ButterflyColors

@Composable
fun Account() {
    val session = LocalButterflySession.current
    val feedback = LocalButterflyFeedback.current
    var name by remember { mutableStateOf(session.user?.name.orEmpty()) }
    var email by remember { mutableStateOf(session.user?.email.orEmpty()) }

    ButterflyPage(
        title = if (session.isAuthenticated) "Mon compte" else "Créer un compte",
        subtitle = "Rejoins Butterfly pour synchroniser notes, méditations et groupes.",
        background = Res.drawable.butterfly,
    ) {
        if (session.isAuthenticated) {
            GlassCard {
                SectionLabel("Profil actif")
                Text(
                    text = session.user?.name.orEmpty(),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = session.user?.email.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = ButterflyColors.MistMuted,
                )
                Text(
                    text = "${session.joinedGroupIds.size} groupe(s) lié(s)",
                    style = MaterialTheme.typography.bodySmall,
                    color = ButterflyColors.SoftGold,
                )
                Button(
                    onClick = {
                        session.signOut()
                        feedback.notify("Session terminée. À bientôt.")
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Se déconnecter")
                }
            }
            PinCard(
                title = "Ton espace spirituel",
                subtitle = "Tes notes et méditations restent privées jusqu'à partage en groupe.",
                height = 170.dp,
                image = Res.drawable.pray,
                badge = "Privé",
            )
        } else {
            GlassCard {
                SectionLabel("Nouveau compte")
                AuthField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Nom complet",
                    placeholder = "Ex: Elie Oko",
                )
                AuthField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    placeholder = "ex: elie@butterfly.app",
                )
                Button(
                    onClick = {
                        when {
                            name.isBlank() || email.isBlank() -> feedback.toast("Complète nom et email.")
                            !email.contains("@") -> feedback.toast("Email invalide.")
                            else -> {
                                session.createAccount(name, email)
                                feedback.notify("Compte créé. Bienvenue sur Butterfly.")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Créer mon compte")
                }
            }
            PinCard(
                title = "Pourquoi un compte ?",
                subtitle = "Sauvegarde tes notes, rejoins des groupes et suis ta progression.",
                height = 190.dp,
                image = Res.drawable.couple,
                badge = "Communauté",
            )
        }
    }
}

@Composable
private fun AuthField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = ButterflyColors.SoftBlue,
            unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
            focusedLabelColor = ButterflyColors.SoftBlue,
            unfocusedLabelColor = ButterflyColors.MistMuted,
            cursorColor = ButterflyColors.SoftBlue,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
        ),
    )
}
