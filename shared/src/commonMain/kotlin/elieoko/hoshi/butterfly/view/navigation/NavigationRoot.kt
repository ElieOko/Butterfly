package elieoko.hoshi.butterfly.view.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    NavigationBar(
                        modifier = Modifier.navigationBarsPadding(),
                        containerColor = ButterflyColors.NightElevated.copy(alpha = 0.96f),
                    ) {
                        for (route in Route.entries) {
                            val routeEmoji = route.emoji
                            val routeLabel = route.label
                            NavigationBarItem(
                                selected = selectedRoute == route,
                                onClick = { selectedRouteName = route.name },
                                icon = { Text(routeEmoji) },
                                label = { Text(routeLabel) },
                                alwaysShowLabel = false,
                            )
                        }
                    }
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
