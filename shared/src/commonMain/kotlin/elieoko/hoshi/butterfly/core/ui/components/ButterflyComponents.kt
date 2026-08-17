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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.bible
import butterfly.shared.generated.resources.books
import butterfly.shared.generated.resources.butterfly
import butterfly.shared.generated.resources.couple
import butterfly.shared.generated.resources.hand
import butterfly.shared.generated.resources.pray
import elieoko.hoshi.butterfly.design.ButterflyColors
import elieoko.hoshi.butterfly.design.SpiritualGradients
import elieoko.hoshi.butterfly.design.SpiritualImagery
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

object ButterflySpacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 32.dp
}

@Composable
fun ImmersiveBackground(
    imageUrl: String? = null,
    image: DrawableResource? = null,
    dimAlpha: Float = 0.72f,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            imageUrl != null -> {
                SpiritualNetworkImage(
                    url = imageUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
            image != null -> {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(ButterflyColors.Midnight, ButterflyColors.Night),
                            ),
                        ),
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SpiritualGradients.auroraVeil),
        )
        AuroraOverlay(modifier = Modifier.fillMaxSize())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            ButterflyColors.Night.copy(alpha = (dimAlpha - 0.20f).coerceAtLeast(0.35f)),
                            ButterflyColors.Night.copy(alpha = 0.92f),
                        ),
                    ),
                ),
        )
    }
}

@Composable
fun ButterflyPage(
    title: String,
    subtitle: String,
    backgroundUrl: String? = null,
    background: DrawableResource = Res.drawable.butterfly,
    kicker: String? = null,
    topContent: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ImmersiveBackground(
            imageUrl = backgroundUrl,
            image = if (backgroundUrl == null) background else null,
            dimAlpha = 0.68f,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(horizontal = ButterflySpacing.lg),
            verticalArrangement = Arrangement.spacedBy(ButterflySpacing.lg),
            contentPadding = PaddingValues(
                top = ButterflySpacing.lg,
                bottom = ButterflySpacing.xxl + 16.dp,
            ),
        ) {
            item {
                EditorialPageHeader(
                    title = title,
                    subtitle = subtitle,
                    kicker = kicker,
                )
            }
            if (topContent != null) {
                item { topContent() }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(ButterflySpacing.lg),
                    content = content,
                )
            }
        }
    }
}

@Composable
fun PinCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    height: Dp = 180.dp,
    imageUrl: String? = null,
    image: DrawableResource = Res.drawable.pray,
    badge: String? = null,
    accent: Color = ButterflyColors.SoftGold,
    onClick: (() -> Unit)? = null,
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.97f else 1f,
        animationSpec = tween(180),
        label = "pin-scale",
    )
    val shape = RoundedCornerShape(24.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .shadow(12.dp, RoundedCornerShape(22.dp), ambientColor = Color.Black.copy(0.35f))
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(shape)
            .border(1.dp, SpiritualGradients.sacredBorder, shape)
            .clickable(enabled = onClick != null) {
                pressed = !pressed
                onClick?.invoke()
            },
    ) {
        if (imageUrl != null) {
            SpiritualNetworkImage(
                url = imageUrl,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        } else {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            ButterflyColors.Night.copy(alpha = 0.15f),
                            ButterflyColors.Night.copy(alpha = 0.72f),
                            ButterflyColors.Night.copy(alpha = 0.96f),
                        ),
                    ),
                ),
        )
        if (badge != null) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(ButterflySpacing.md),
                color = accent.copy(alpha = 0.22f),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, accent.copy(alpha = 0.45f)),
            ) {
                Text(
                    text = badge,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = accent,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(ButterflySpacing.lg),
            verticalArrangement = Arrangement.spacedBy(ButterflySpacing.xs),
        ) {
            Text(
                title,
                color = ButterflyColors.WarmCream,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                subtitle,
                color = ButterflyColors.WarmCream.copy(alpha = 0.78f),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
fun PinCardPair(
    left: @Composable () -> Unit,
    right: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(ButterflySpacing.md),
    ) {
        Box(modifier = Modifier.weight(1f)) { left() }
        Box(modifier = Modifier.weight(1f)) { right() }
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val shape = RoundedCornerShape(22.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .border(1.dp, Color.White.copy(alpha = 0.08f), shape)
            .drawBehind {
                drawRect(SpiritualGradients.glassSurface)
                drawRect(ButterflyColors.Glass)
            },
    ) {
        Column(
            modifier = Modifier.padding(ButterflySpacing.lg),
            verticalArrangement = Arrangement.spacedBy(ButterflySpacing.md),
            content = content,
        )
    }
}

@Composable
fun PillRow(
    labels: List<String>,
    onSelected: ((String) -> Unit)? = null,
) {
    var selected by remember { mutableStateOf(labels.firstOrNull()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(ButterflySpacing.sm),
    ) {
        labels.forEach { label ->
            val active = label == selected
            val shape = RoundedCornerShape(999.dp)
            Box(
                modifier = Modifier
                    .clip(shape)
                    .then(
                        if (active) {
                            Modifier.background(SpiritualGradients.ctaButton)
                        } else {
                            Modifier
                                .background(Color.White.copy(alpha = 0.06f))
                                .border(1.dp, Color.White.copy(alpha = 0.12f), shape)
                        },
                    )
                    .clickable {
                        selected = label
                        onSelected?.invoke(label)
                    }
                    .padding(horizontal = 16.dp, vertical = 9.dp),
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (active) ButterflyColors.Night else ButterflyColors.WarmCream,
                    fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun SectionLabel(text: String) {
    SacredSectionLabel(text = text)
}

@Composable
fun MetricChip(
    label: String,
    value: String,
    accent: Color = ButterflyColors.SoftBlue,
) {
    val shape = RoundedCornerShape(20.dp)
    Box(
        modifier = Modifier
            .clip(shape)
            .border(1.dp, accent.copy(alpha = 0.35f), shape)
            .background(accent.copy(alpha = 0.08f))
            .padding(horizontal = 16.dp, vertical = 14.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(accent, CircleShape),
                )
                Text(label, style = MaterialTheme.typography.labelSmall, color = ButterflyColors.MistMuted)
            }
            Text(
                value,
                style = MaterialTheme.typography.titleMedium,
                color = ButterflyColors.WarmCream,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

fun butterflyImageUrlFor(index: Int): String = SpiritualImagery.cardAt(index)

fun butterflyImageFor(index: Int): DrawableResource = when (index % 6) {
    0 -> Res.drawable.butterfly
    1 -> Res.drawable.pray
    2 -> Res.drawable.couple
    3 -> Res.drawable.hand
    4 -> Res.drawable.bible
    else -> Res.drawable.books
}
