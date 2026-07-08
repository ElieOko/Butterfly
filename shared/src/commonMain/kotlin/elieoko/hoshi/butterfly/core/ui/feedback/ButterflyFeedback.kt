package elieoko.hoshi.butterfly.core.ui.feedback

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ButterflyFeedback(
    val snackbarHostState: SnackbarHostState,
    private val scope: CoroutineScope,
) {
    fun toast(message: String) {
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short,
            )
        }
    }

    fun notify(message: String, actionLabel: String? = null) {
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = true,
                duration = SnackbarDuration.Long,
            )
        }
    }
}

val LocalButterflyFeedback = staticCompositionLocalOf<ButterflyFeedback> {
    error("ButterflyFeedback not provided")
}

@Composable
fun rememberButterflyFeedback(): ButterflyFeedback {
    val host = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    return remember(host, scope) { ButterflyFeedback(host, scope) }
}

@Composable
fun ProvideButterflyFeedback(
    feedback: ButterflyFeedback,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalButterflyFeedback provides feedback, content = content)
}
