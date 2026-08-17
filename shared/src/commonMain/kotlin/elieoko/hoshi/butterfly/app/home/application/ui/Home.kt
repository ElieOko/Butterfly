package elieoko.hoshi.butterfly.app.home.application.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import elieoko.hoshi.butterfly.core.session.LocalButterflySession
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.ButterflySpacing
import elieoko.hoshi.butterfly.core.ui.components.GlassCard
import elieoko.hoshi.butterfly.core.ui.components.MetricChip
import elieoko.hoshi.butterfly.core.ui.components.PillRow
import elieoko.hoshi.butterfly.core.ui.components.PinCard
import elieoko.hoshi.butterfly.core.ui.components.PinCardPair
import elieoko.hoshi.butterfly.core.ui.components.SacredOrnament
import elieoko.hoshi.butterfly.core.ui.components.SacredPrimaryButton
import elieoko.hoshi.butterfly.core.ui.components.SacredSectionLabel
import elieoko.hoshi.butterfly.core.ui.components.VerseHeroCard
import elieoko.hoshi.butterfly.core.ui.feedback.LocalButterflyFeedback
import elieoko.hoshi.butterfly.design.ButterflyColors
import elieoko.hoshi.butterfly.design.SpiritualImagery

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
    val greeting = session.user?.name?.let { "Bonjour, $it" } ?: "Bienvenue"

    ButterflyPage(
        title = greeting,
        subtitle = "Un sanctuaire numérique pour lire, méditer et grandir chaque jour.",
        backgroundUrl = SpiritualImagery.heroHome,
        kicker = "Butterfly",
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(ButterflySpacing.sm),
        ) {
            MetricChip("Série", "21 j", accent = ButterflyColors.SoftGold)
            MetricChip("Notes", "12", accent = ButterflyColors.SoftViolet)
            MetricChip("Groupes", "${session.joinedGroupIds.size}", accent = ButterflyColors.SoftBlue)
        }

        VerseHeroCard(
            verse = "Ta parole est une lampe à mes pieds, et une lumière sur mon sentier.",
            reference = "Psaume 119:105",
            imageUrl = SpiritualImagery.crossLight,
            onClick = {
                feedback.toast("Verset sauvegardé dans tes notes.")
                onOpenBible()
            },
        )

        SacredOrnament()
        SacredSectionLabel("Explorer")

        PinCardPair(
            left = {
                PinCard(
                    title = "Bible",
                    subtitle = "Lecture immersive",
                    height = 220.dp,
                    imageUrl = SpiritualImagery.bibleOpen,
                    badge = "Lire",
                    accent = ButterflyColors.SoftBlue,
                    onClick = onOpenBible,
                )
            },
            right = {
                Column(verticalArrangement = Arrangement.spacedBy(ButterflySpacing.md)) {
                    PinCard(
                        title = "Méditation",
                        subtitle = "6 min de calme",
                        height = 120.dp,
                        imageUrl = SpiritualImagery.meditationCalm,
                        badge = "Zen",
                        accent = ButterflyColors.RoseDawn,
                        onClick = onOpenMeditation,
                    )
                    PinCard(
                        title = "Notes",
                        subtitle = "Ton journal",
                        height = 108.dp,
                        imageUrl = SpiritualImagery.scriptureDesk,
                        badge = "Écrire",
                        accent = ButterflyColors.SoftViolet,
                        onClick = onOpenNotes,
                    )
                }
            },
        )

        PinCard(
            title = "Groupes & communauté",
            subtitle = "Partage, prière et progression ensemble — rejoins une famille spirituelle.",
            height = 200.dp,
            imageUrl = SpiritualImagery.worshipCommunity,
            badge = "Communauté",
            accent = ButterflyColors.SoftGold,
            onClick = onOpenGroups,
        )

        GlassCard {
            SacredSectionLabel("Raccourcis")
            PillRow(
                labels = listOf("Prière du soir", "Lecture couple", "Défi 7 jours", "Favoris"),
                onSelected = { feedback.toast("$it ouvert") },
            )
            SacredPrimaryButton(
                text = if (session.isAuthenticated) "Voir mon compte" else "Créer un compte",
                onClick = {
                    if (session.isAuthenticated) {
                        feedback.notify("Compte actif : ${session.user?.email}")
                    }
                    onOpenAccount()
                },
            )
        }
    }
}
