package elieoko.hoshi.butterfly.core.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.bible
import butterfly.shared.generated.resources.books
import org.jetbrains.compose.resources.painterResource

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

@Composable
@Preview(showBackground = true)
fun MCard(){
    Column(modifier = Modifier.background(Color.Black.copy(0.9F)), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = painterResource(Res.drawable.bible),null, modifier = Modifier.size(16.dp), tint = Color.White)
        Spacer(Modifier.height(2.dp))
        Text("Verse of the day", color = Color.White.copy(0.8F))
        Spacer(Modifier.height(7.dp))
        Text("I am the true vine, and my Father is the husbandman.", fontSize = 19.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = Color.White)
        SuggestionChip(
            colors = SuggestionChipDefaults.suggestionChipColors(containerColor = Color.Black),
            elevation = SuggestionChipDefaults.suggestionChipElevation(5.dp),
            label = {Text("John 15:1", color = Color.White)},
            onClick = {},
            border= BorderStroke(3.dp,Color.Black),
            icon = { Icon(painter = painterResource(Res.drawable.books), null, modifier = Modifier.size(14.dp), tint = Color.White) }
        )
    }
}
