package elieoko.hoshi.butterfly.core.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun ButterflyPage(
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(bottom = 28.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(
                subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        item {
            ButterflyHeroCard()
        }
        item {
            Column(content = content)
        }
    }
}

@Composable
fun ButterflyHeroCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.95f),
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.9f),
                        ),
                    ),
                )
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "Butterfly logo",
                    modifier = Modifier.size(34.dp),
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text("Butterfly", color = Color.White, fontWeight = FontWeight.Bold)
                Text(
                    "Croissance spirituelle, en solo et en communaute.",
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Button(onClick = {}) {
                Text("Prier")
            }
        }
    }
}

@Composable
fun OrganicCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    accentA: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.82f),
    accentB: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 1.02f else 1f,
        animationSpec = tween(durationMillis = 180),
        label = "organic-scale",
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(enabled = onClick != null) {
                pressed = !pressed
                onClick?.invoke()
            },
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 12.dp, bottomStart = 12.dp, bottomEnd = 28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(accentA, accentB)))
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(title, color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = Color.White.copy(alpha = 0.92f), style = MaterialTheme.typography.bodySmall)
            content()
        }
    }
}

@Composable
fun PillRow(labels: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        labels.forEach { label ->
            Surface(
                shape = RoundedCornerShape(999.dp),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.86f),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f),
                        shape = RoundedCornerShape(999.dp),
                    )
                    .clickable {},
            ) {
                Text(
                    text = label,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun ButterflyBadge(label: String) {
    Surface(shape = CutCornerShape(8.dp), color = Color.White.copy(alpha = 0.2f)) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            style = MaterialTheme.typography.labelMedium,
            color = Color.White,
        )
    }
}
