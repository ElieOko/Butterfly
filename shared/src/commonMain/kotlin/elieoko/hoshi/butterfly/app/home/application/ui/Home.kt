package elieoko.hoshi.butterfly.app.home.application.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.butterfly
import butterfly.shared.generated.resources.couple
import butterfly.shared.generated.resources.hand
import butterfly.shared.generated.resources.pray
import elieoko.hoshi.butterfly.core.session.LocalButterflySession
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.ButterflySpacing
import elieoko.hoshi.butterfly.core.ui.components.GlassCard
import elieoko.hoshi.butterfly.core.ui.components.MetricChip
import elieoko.hoshi.butterfly.core.ui.components.PinCard
import elieoko.hoshi.butterfly.core.ui.components.PillRow
import elieoko.hoshi.butterfly.core.ui.components.SectionLabel
import elieoko.hoshi.butterfly.core.ui.feedback.LocalButterflyFeedback

@Composable
fun Home(
    onOpenBible: () -> Unit,
    onOpenNotes: () -> Unit,
    onOpenMeditation: () -> Unit,
    onOpenGroups: () -> Unit,
    onOpenAccount: () -> Unit,
) {
    val session = LocalButterflySession.current
    val feedback = LocalButterflyFeedback.current
    val greeting = session.user?.name?.let { "Bonjour $it" } ?: "Bienvenue sur Butterfly"

    ButterflyPage(
        title = greeting,
        subtitle = "Un board spirituel calme : Bible, notes, méditation et communauté.",
        background = Res.drawable.butterfly,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(ButterflySpacing.sm),
        ) {
            MetricChip("Série", "21 j")
            MetricChip("Notes", "12")
            MetricChip("Groupes", "${session.joinedGroupIds.size}")
        }

        PinCard(
            title = "Verset du jour",
            subtitle = "Ta parole est une lampe à mes pieds — Psaume 119:105",
            height = 220.dp,
            image = Res.drawable.pray,
            badge = "Aujourd'hui",
            onClick = {
                feedback.toast("Verset sauvegardé dans tes notes.")
                onOpenBible()
            },
        )

        SectionLabel("Explorer")
        PinCard(
            title = "Bible",
            subtitle = "Lecture immersive et recherche rapide",
            height = 168.dp,
            image = Res.drawable.hand,
            badge = "Lire",
            onClick = onOpenBible,
        )
        PinCard(
            title = "Notes",
            subtitle = "Journal spirituel personnel",
            height = 200.dp,
            image = Res.drawable.couple,
            badge = "Écrire",
            onClick = onOpenNotes,
        )
        PinCard(
            title = "Méditation",
            subtitle = "Sessions courtes pour retrouver le calme",
            height = 180.dp,
            image = Res.drawable.pray,
            badge = "Calme",
            onClick = onOpenMeditation,
        )
        PinCard(
            title = "Groupes",
            subtitle = "Partage, prière et progression commune",
            height = 160.dp,
            image = Res.drawable.butterfly,
            badge = "Rejoindre",
            onClick = onOpenGroups,
        )

        GlassCard {
            SectionLabel("Raccourcis")
            PillRow(
                labels = listOf("Prière du soir", "Lecture couple", "Défi 7 jours", "Favoris"),
                onSelected = { feedback.toast("$it ouvert") },
            )
            Button(
                onClick = {
                    if (session.isAuthenticated) {
                        feedback.notify("Compte actif : ${session.user?.email}")
                        onOpenAccount()
                    } else {
                        onOpenAccount()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(if (session.isAuthenticated) "Voir mon compte" else "Créer un compte")
            }
        }
    }
}
