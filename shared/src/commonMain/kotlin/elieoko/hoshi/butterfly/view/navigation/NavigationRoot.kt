package elieoko.hoshi.butterfly.view.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import elieoko.hoshi.butterfly.app.auth.application.ui.Account
import elieoko.hoshi.butterfly.app.bible.application.ui.Bible
import elieoko.hoshi.butterfly.app.group.application.ui.Group
import elieoko.hoshi.butterfly.app.home.application.ui.Home
import elieoko.hoshi.butterfly.app.meditation.application.ui.Meditation
import elieoko.hoshi.butterfly.app.note.application.ui.Notes
import elieoko.hoshi.butterfly.app.onboarding.application.ui.Onboarding
import elieoko.hoshi.butterfly.core.session.LocalButterflySession
import elieoko.hoshi.butterfly.core.session.rememberButterflySession
import elieoko.hoshi.butterfly.core.ui.feedback.ProvideButterflyFeedback
import elieoko.hoshi.butterfly.core.ui.feedback.rememberButterflyFeedback
import elieoko.hoshi.butterfly.design.ButterflyColors
import elieoko.hoshi.butterfly.design.SpiritualGradients

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val session = rememberButterflySession()
    val feedback = rememberButterflyFeedback()
    var onboardingFinished by remember { mutableStateOf(false) }
    var selectedRouteName by remember { mutableStateOf(Route.Home.name) }
    val selectedRoute = Route.entries.firstOrNull { it.name == selectedRouteName } ?: Route.Home

    CompositionLocalProvider(LocalButterflySession provides session) {
        ProvideButterflyFeedback(feedback) {
            if (!onboardingFinished) {
                Onboarding(onFinish = {
                    onboardingFinished = true
                    feedback.notify("Bienvenue. Explore Bible, Notes et Méditation.")
                })
                return@ProvideButterflyFeedback
            }

            Scaffold(
                modifier = modifier.fillMaxSize(),
                containerColor = ButterflyColors.Night,
                snackbarHost = {
                    SnackbarHost(hostState = feedback.snackbarHostState) { data ->
                        Snackbar(
                            snackbarData = data,
                            containerColor = ButterflyColors.NightCard,
                            contentColor = Color.White,
                            actionColor = ButterflyColors.SoftGold,
                        )
                    }
                },
                bottomBar = {
                    SacredBottomBar(
                        selectedRoute = selectedRoute,
                        onSelected = { selectedRouteName = it.name },
                    )
                },
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {
                    Crossfade(targetState = selectedRoute, label = "app-route") { route ->
                        when (route) {
                            Route.Home -> Home(
                                onOpenBible = { selectedRouteName = Route.Bible.name },
                                onOpenNotes = { selectedRouteName = Route.Notes.name },
                                onOpenMeditation = { selectedRouteName = Route.Meditation.name },
                                onOpenGroups = { selectedRouteName = Route.Groups.name },
                                onOpenAccount = { selectedRouteName = Route.Account.name },
                            )
                            Route.Bible -> Bible()
                            Route.Notes -> Notes()
                            Route.Meditation -> Meditation()
                            Route.Groups -> Group()
                            Route.Account -> Account()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SacredBottomBar(
    selectedRoute: Route,
    onSelected: (Route) -> Unit,
) {
    val barShape = RoundedCornerShape(32.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 18.dp, vertical = 10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(barShape)
                .border(1.dp, SpiritualGradients.sacredBorder, barShape)
                .background(SpiritualGradients.navBarFill)
                .padding(horizontal = 4.dp, vertical = 8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Route.entries.forEach { route ->
                    SacredTabItem(
                        route = route,
                        selected = route == selectedRoute,
                        onClick = { onSelected(route) },
                    )
                }
            }
        }
    }
}

@Composable
private fun SacredTabItem(
    route: Route,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val iconColor by animateColorAsState(
        targetValue = if (selected) ButterflyColors.SoftGold else ButterflyColors.MistMuted,
        animationSpec = tween(220),
        label = "tab-icon",
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) ButterflyColors.WarmCream else ButterflyColors.MistMuted.copy(alpha = 0.7f),
        animationSpec = tween(220),
        label = "tab-text",
    )

    Column(
        modifier = Modifier
            .width(52.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(ButterflyColors.SoftGold.copy(alpha = 0.14f), CircleShape),
                )
            }
            Icon(
                imageVector = route.icon,
                contentDescription = route.label,
                modifier = Modifier.size(22.dp),
                tint = iconColor,
            )
        }
        if (selected) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(ButterflyColors.SoftGold, CircleShape),
            )
        } else {
            Box(modifier = Modifier.height(4.dp))
        }
        Text(
            text = route.label,
            color = textColor,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
    }
}
