package elieoko.hoshi.butterfly.view.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import elieoko.hoshi.butterfly.app.about.application.ui.About
import elieoko.hoshi.butterfly.app.assistant.application.ui.Assistant
import elieoko.hoshi.butterfly.app.bible.application.ui.Bible
import elieoko.hoshi.butterfly.app.group.application.ui.Group
import elieoko.hoshi.butterfly.app.home.application.ui.Home
import elieoko.hoshi.butterfly.app.onboarding.application.ui.Onboarding
import elieoko.hoshi.butterfly.app.plan.application.ui.Plan
import elieoko.hoshi.butterfly.core.ui.components.ButterflyBackground

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    var onboardingFinished by remember { mutableStateOf(false) }
    var selectedRoute by remember { mutableStateOf<Route>(Route.Home) }

    if (!onboardingFinished) {
        Onboarding(onFinish = { onboardingFinished = true })
        return
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(
                modifier = Modifier.navigationBarsPadding(),
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
            ) {
                Route.mainItems.forEach { route ->
                    NavigationBarItem(
                        selected = selectedRoute == route,
                        onClick = { selectedRoute = route },
                        icon = { Text(route.emoji) },
                        label = { Text(route.label) },
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
            ButterflyBackground()
            Crossfade(targetState = selectedRoute, label = "app-route") { route ->
                when (route) {
                    Route.Home -> Home(
                        onOpenGroups = { selectedRoute = Route.Group },
                        onOpenAssistant = { selectedRoute = Route.Assistant },
                        onOpenAbout = { selectedRoute = Route.About },
                    )
                    Route.Bible -> Bible()
                    Route.Plan -> Plan()
                    Route.Group -> Group()
                    Route.Assistant -> Assistant()
                    Route.About -> About()
                }
            }
        }
    }
}
