package elieoko.hoshi.butterfly.core.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elieoko.hoshi.butterfly.design.ButterflyColors
import elieoko.hoshi.butterfly.design.SpiritualGradients

@Composable
fun AuroraOverlay(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "aurora")
    val drift by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 9000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "aurora-drift",
    )

    Box(
        modifier = modifier
            .graphicsLayer { alpha = 0.9f }
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        ButterflyColors.SoftViolet.copy(alpha = 0.14f + drift * 0.06f),
                        Color.Transparent,
                        ButterflyColors.SoftBlue.copy(alpha = 0.10f + drift * 0.05f),
                        Color.Transparent,
                        ButterflyColors.RoseDawn.copy(alpha = 0.08f + drift * 0.04f),
                    ),
                    start = Offset(drift * 400f, 0f),
                    end = Offset(800f + drift * 200f, 600f),
                ),
            ),
    )
}

@Composable
fun EditorialPageHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    kicker: String? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(ButterflySpacing.sm),
    ) {
        if (kicker != null) {
            Text(
                text = kicker.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = ButterflyColors.SoftGold,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.2.sp,
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Light,
                letterSpacing = (-0.5).sp,
            ),
            color = ButterflyColors.WarmCream,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = ButterflyColors.MistMuted,
            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
        )
        SacredOrnament(modifier = Modifier.padding(top = ButterflySpacing.xs))
    }
}

@Composable
fun SacredOrnament(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color.Transparent, ButterflyColors.SoftGold.copy(alpha = 0.45f)),
                    ),
                ),
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(6.dp)
                .background(SpiritualGradients.goldShimmer, CircleShape),
        )
        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(
                    Brush.horizontalGradient(
                        listOf(ButterflyColors.SoftGold.copy(alpha = 0.45f), Color.Transparent),
                    ),
                ),
        )
    }
}

@Composable
fun SacredSectionLabel(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(ButterflySpacing.sm),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Serif),
            fontWeight = FontWeight.Normal,
            color = ButterflyColors.WarmCream,
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(ButterflyColors.SoftGold.copy(alpha = 0.5f), Color.Transparent),
                    ),
                ),
        )
    }
}

@Composable
fun VerseHeroCard(
    verse: String,
    reference: String,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    onClick: (() -> Unit)? = null,
) {
    val shape = RoundedCornerShape(28.dp)
  Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .border(1.dp, SpiritualGradients.sacredBorder, shape)
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .drawBehind {
                drawRect(Brush.verticalGradient(listOf(ButterflyColors.Midnight, ButterflyColors.NightCard)))
            },
    ) {
        if (imageUrl != null) {
            SpiritualNetworkImage(
                url = imageUrl,
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { alpha = 0.42f },
            )
        }
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(SpiritualGradients.auroraVeil),
        )
        AuroraOverlay(modifier = Modifier.matchParentSize())
        Column(
            modifier = Modifier
                .padding(horizontal = ButterflySpacing.xl, vertical = ButterflySpacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(ButterflySpacing.md),
        ) {
            Text(
                text = "Verset du jour",
                style = MaterialTheme.typography.labelSmall,
                color = ButterflyColors.SoftGold,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.4.sp,
            )
            Text(
                text = verse,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Normal,
                    lineHeight = MaterialTheme.typography.titleLarge.fontSize * 1.45f,
                ),
                color = ButterflyColors.WarmCream,
                textAlign = TextAlign.Center,
            )
            SacredOrnament()
            Text(
                text = reference,
                style = MaterialTheme.typography.labelLarge,
                color = ButterflyColors.SoftBlue,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun SacredPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(18.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(SpiritualGradients.ctaButton)
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = ButterflyColors.Night,
            fontWeight = FontWeight.Bold,
        )
    }
}
