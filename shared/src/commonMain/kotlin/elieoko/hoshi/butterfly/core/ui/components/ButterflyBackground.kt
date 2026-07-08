package elieoko.hoshi.butterfly.core.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ButterflyBackground(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "butterfly-bg")
    val drift by transition.animateFloat(
        initialValue = -30f,
        targetValue = 28f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "bg-drift",
    )

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(320.dp)
                .offset(x = drift.dp, y = (-80).dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
        )
        Box(
            modifier = Modifier
                .size(280.dp)
                .offset(x = 220.dp, y = (100f - drift).dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)),
        )
        Box(
            modifier = Modifier
                .size(180.dp)
                .offset(x = 150.dp, y = 490.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.08f)),
        )
    }
}
