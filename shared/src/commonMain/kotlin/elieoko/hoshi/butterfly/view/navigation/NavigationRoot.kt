package elieoko.hoshi.butterfly.view.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
                topBar = {
                    IosTopBar(
                        route = selectedRoute,
                        userName = session.user?.name,
                    )
                },
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
                    IosBottomBar(
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
private fun IosTopBar(
    route: Route,
    userName: String?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            color = ButterflyColors.Glass,
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.10f)),
            shadowElevation = 10.dp,
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = userName?.let { "Bonjour, $it" } ?: "Butterfly",
                    color = Color.White.copy(alpha = 0.72f),
                    style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                )
                Text(
                    text = "${route.emoji} ${route.label}",
                    color = Color.White,
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
private fun IosBottomBar(
    selectedRoute: Route,
    onSelected: (Route) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 14.dp, vertical = 8.dp),
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(34.dp),
            color = ButterflyColors.Glass,
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.10f)),
            shadowElevation = 16.dp,
        ) {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Route.entries.forEach { route ->
                    IosTabItem(
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
private fun IosTabItem(
    route: Route,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val bg = if (selected) Color.White.copy(alpha = 0.16f) else Color.Transparent
    val textColor = if (selected) Color.White else Color.White.copy(alpha = 0.62f)

    Column(
        modifier = Modifier
            .width(68.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(bg)
            .clickable(onClick = onClick)
            .padding(vertical = 7.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = route.emoji,
            modifier = Modifier.fillMaxWidth(),
            color = textColor,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
        Text(
            text = route.label,
            modifier = Modifier.fillMaxWidth(),
            color = textColor,
            style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
            maxLines = 1,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
    }
}
