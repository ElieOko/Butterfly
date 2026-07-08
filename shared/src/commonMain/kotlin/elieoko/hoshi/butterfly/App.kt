package elieoko.hoshi.butterfly

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import elieoko.hoshi.butterfly.design.SpiritualTheme

private enum class AppDestination(val title: String, val emoji: String) {
    Home("Accueil", "🏠"),
    Bible("Bible", "📖"),
    Plans("Plans", "🧩"),
    Ai("Assistant", "✨"),
}

private data class PinTile(
    val category: String,
    val title: String,
    val description: String,
    val badge: String,
    val height: Dp,
    val colorA: Color,
    val colorB: Color,
)

private val boardTiles = listOf(
    PinTile(
        category = "Méditation",
        title = "Silence du matin",
        description = "3 minutes de respiration + Psaume 23",
        badge = "Calme",
        height = 220.dp,
        colorA = Color(0xFF4052BC),
        colorB = Color(0xFF7C65D5),
    ),
    PinTile(
        category = "Couple",
        title = "Question du soir",
        description = "Comment Dieu a agi dans ta journée ?",
        badge = "En duo",
        height = 178.dp,
        colorA = Color(0xFF7252B7),
        colorB = Color(0xFF9A76CC),
    ),
    PinTile(
        category = "Prière",
        title = "Intercession guidée",
        description = "Famille, église, nations • 7 min",
        badge = "Focus",
        height = 246.dp,
        colorA = Color(0xFF1E2A78),
        colorB = Color(0xFF3A4FC4),
    ),
    PinTile(
        category = "Bible",
        title = "Romains en profondeur",
        description = "Notes visuelles + synthèse chapitre 8",
        badge = "Étude",
        height = 202.dp,
        colorA = Color(0xFF6550A5),
        colorB = Color(0xFF8A5FB0),
    ),
    PinTile(
        category = "Défi",
        title = "7 jours de gratitude",
        description = "Un remerciement concret chaque jour",
        badge = "Streak",
        height = 188.dp,
        colorA = Color(0xFF9A6A2B),
        colorB = Color(0xFFC99847),
    ),
    PinTile(
        category = "Communauté",
        title = "Groupe de maison",
        description = "18 membres ont terminé le jour 4",
        badge = "Live",
        height = 232.dp,
        colorA = Color(0xFF2D4A87),
        colorB = Color(0xFF4568AE),
    ),
)

@Composable
@Preview
fun App() {
    SpiritualTheme {
        var selectedDestination by remember { mutableStateOf(AppDestination.Home) }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.navigationBarsPadding(),
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.96f),
                ) {
                    AppDestination.entries.forEach { destination ->
                        NavigationBarItem(
                            selected = selectedDestination == destination,
                            onClick = { selectedDestination = destination },
                            icon = { Text(destination.emoji) },
                            label = { Text(destination.title) },
                        )
                    }
                }
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                AuroraBackground()
                Crossfade(
                    targetState = selectedDestination,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    label = "screen-fade",
                ) { destination ->
                    when (destination) {
                        AppDestination.Home -> HomeScreen()
                        AppDestination.Bible -> BibleScreen()
                        AppDestination.Plans -> PlansScreen()
                        AppDestination.Ai -> AssistantScreen()
                    }
                }
            }
        }
    }
}

@Composable
private fun AuroraBackground() {
    val transition = rememberInfiniteTransition(label = "aurora")
    val drift by transition.animateFloat(
        initialValue = -22f,
        targetValue = 24f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "aurora-drift",
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (drift).dp, y = (-96).dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.11f)),
        )
        Box(
            modifier = Modifier
                .size(280.dp)
                .offset(x = 220.dp, y = (120f - drift).dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)),
        )
    }
}

@Composable
private fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Text("Inspiration board", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(
                "Un rendu style Pinterest avec cartes vivantes, formes organiques et interactions fluides.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        item { HeroVerseCard() }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                CompactStat("Série", "19 jours", modifier = Modifier.weight(1f))
                CompactStat("Objectifs", "5/6", modifier = Modifier.weight(1f))
                CompactStat("Groupes", "3 actifs", modifier = Modifier.weight(1f))
            }
        }
        item { SectionTitle("Flow spirituel du jour") }
        item { PinterestBoard() }
        item { Spacer(modifier = Modifier.height(12.dp)) }
    }
}

@Composable
private fun HeroVerseCard() {
    val transition = rememberInfiniteTransition(label = "hero")
    val pulse by transition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "hero-pulse",
    )

    Card(
        shape = RoundedCornerShape(topStart = 34.dp, topEnd = 18.dp, bottomStart = 18.dp, bottomEnd = 34.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
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
                .padding(20.dp),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(90.dp)
                    .graphicsLayer {
                        scaleX = pulse
                        scaleY = pulse
                    }
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.18f)),
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(shape = CutCornerShape(8.dp), color = Color.White.copy(alpha = 0.18f)) {
                    Text(
                        "Verset du jour",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                Text(
                    "\"Ta parole est une lampe à mes pieds, une lumière sur mon sentier.\"",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
                Text("Psaume 119:105", color = Color.White.copy(alpha = 0.86f))
                Button(onClick = {}) { Text("Démarrer la méditation") }
            }
        }
    }
}

@Composable
private fun CompactStat(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.88f)),
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun SectionTitle(label: String) {
    Text(label, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun PinterestBoard() {
    val leftColumn = boardTiles.filterIndexed { index, _ -> index % 2 == 0 }
    val rightColumn = boardTiles.filterIndexed { index, _ -> index % 2 != 0 }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            leftColumn.forEachIndexed { index, tile ->
                PinMosaicCard(tile = tile, dynamicCorner = if (index % 2 == 0) 30.dp else 22.dp)
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            rightColumn.forEachIndexed { index, tile ->
                PinMosaicCard(tile = tile, dynamicCorner = if (index % 2 == 0) 18.dp else 32.dp)
            }
        }
    }
}

@Composable
private fun PinMosaicCard(
    tile: PinTile,
    dynamicCorner: Dp,
) {
    var expanded by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (expanded) 1.03f else 1f,
        animationSpec = tween(durationMillis = 260),
        label = "tile-scale",
    )
    val elevation by animateDpAsState(
        targetValue = if (expanded) 14.dp else 2.dp,
        animationSpec = tween(durationMillis = 260),
        label = "tile-elevation",
    )
    val floatingDrift by rememberInfiniteTransition(label = "tile-orb").animateFloat(
        initialValue = -12f,
        targetValue = 14f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "tile-drift",
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(tile.height)
            .shadow(elevation = elevation, shape = RoundedCornerShape(dynamicCorner))
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                rotationZ = if (expanded) -1.2f else 0f
            }
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(
            topStart = dynamicCorner,
            topEnd = 14.dp,
            bottomStart = 16.dp,
            bottomEnd = dynamicCorner,
        ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(listOf(tile.colorA, tile.colorB)))
                .padding(14.dp),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (14f + floatingDrift).dp, y = (-20).dp)
                    .size(88.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.16f)),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = (-18).dp, y = (8f + floatingDrift).dp)
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.14f)),
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Surface(color = Color.White.copy(alpha = 0.2f), shape = CutCornerShape(8.dp)) {
                    Text(
                        text = tile.category,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        tile.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        tile.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f),
                    )
                    AnimatedVisibility(visible = expanded) {
                        Text(
                            "• ${tile.badge} • tapoter pour revenir",
                            color = Color.White.copy(alpha = 0.86f),
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(top = 4.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BibleScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(6.dp))
            SectionTitle("Lecture immersive")
            Text("Design lisible, contrasté et animé pour la méditation.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        item {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                placeholder = { Text("Rechercher un verset, un thème, un mot clé...") },
                trailingIcon = { Text("🔎") },
            )
        }
        item {
            ScriptureCard(
                chapter = "Jean 14",
                text = "27 Je vous laisse la paix, je vous donne ma paix...",
                accent = MaterialTheme.colorScheme.primary.copy(alpha = 0.22f),
            )
        }
        item {
            ScriptureCard(
                chapter = "Philippiens 4",
                text = "6 Ne vous inquiétez de rien; mais en toute chose faites connaître vos besoins à Dieu...",
                accent = MaterialTheme.colorScheme.secondary.copy(alpha = 0.24f),
            )
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ActionBubble("🎧 Audio")
                ActionBubble("📝 Notes")
                ActionBubble("⭐ Favori")
                ActionBubble("🤝 Partager")
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun ScriptureCard(
    chapter: String,
    text: String,
    accent: Color,
) {
    var active by remember { mutableStateOf(false) }
    val borderAlpha by animateFloatAsState(
        targetValue = if (active) 0.7f else 0.25f,
        animationSpec = tween(250),
        label = "scripture-border",
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { active = !active },
        shape = RoundedCornerShape(topStart = 26.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 26.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = borderAlpha), RoundedCornerShape(26.dp))
                .background(accent)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(chapter, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
            Text(text, style = MaterialTheme.typography.bodyLarge, lineHeight = MaterialTheme.typography.bodyLarge.lineHeight)
            AnimatedVisibility(active) {
                Text(
                    "Explication IA: Jésus offre une paix durable qui ne dépend pas des circonstances.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun PlansScreen() {
    val categories = listOf("Couple", "Prière", "Guérison", "Leadership", "Famille")
    val plans = listOf(
        "21 jours • Couples alignés avec Dieu" to 0.68f,
        "14 jours • Sortir de l'anxiété par la prière" to 0.34f,
        "30 jours • Discipline spirituelle profonde" to 0.12f,
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(6.dp))
            SectionTitle("Plans ultra visuels")
            Text("Organisation inspirée Pinterest avec cartes éditoriales.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                categories.forEach { category ->
                    ActionBubble(category)
                }
            }
        }
        item {
            plans.forEachIndexed { index, (title, progress) ->
                PlanEditorialCard(
                    title = title,
                    progress = progress,
                    colorA = if (index % 2 == 0) Color(0xFF27398A) else Color(0xFF7656BD),
                    colorB = if (index % 2 == 0) Color(0xFF5367CF) else Color(0xFFA57BD4),
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun PlanEditorialCard(
    title: String,
    progress: Float,
    colorA: Color,
    colorB: Color,
) {
    Card(
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 12.dp, bottomStart = 12.dp, bottomEnd = 30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(colorA, colorB)))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(title, color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                color = MaterialTheme.colorScheme.tertiary,
                trackColor = Color.White.copy(alpha = 0.24f),
            )
            Text("${(progress * 100).toInt()}% accompli", color = Color.White.copy(alpha = 0.92f), style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun AssistantScreen() {
    var expanded by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(6.dp))
            SectionTitle("Assistant IA designé")
            Text("Bulles conversationnelles avec réponses extensibles.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        item {
            BubbleCard(
                left = true,
                text = "Comment retrouver la paix quand je suis stressé ?",
                bubbleColor = MaterialTheme.colorScheme.surface,
            )
        }
        item {
            BubbleCard(
                left = false,
                text = "Commence par Philippiens 4:6-7, puis prie en nommant précisément tes besoins. Je peux te guider en 3 étapes.",
                bubbleColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
                expanded = expanded,
                onToggle = { expanded = !expanded },
            )
        }
        item {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Écris ta question biblique...") },
                trailingIcon = { Text("➤") },
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun BubbleCard(
    left: Boolean,
    text: String,
    bubbleColor: Color,
    expanded: Boolean = false,
    onToggle: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (left) Arrangement.Start else Arrangement.End,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .animateContentSize()
                .clickable(enabled = onToggle != null) { onToggle?.invoke() },
            shape = if (left) {
                RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp, bottomStart = 8.dp, bottomEnd = 18.dp)
            } else {
                RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp, bottomStart = 18.dp, bottomEnd = 8.dp)
            },
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text, style = MaterialTheme.typography.bodyMedium)
                if (onToggle != null) {
                    Text(
                        if (expanded) "Masquer les actions" else "Voir actions",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                AnimatedVisibility(visible = expanded) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        ActionBubble("Prière guidée")
                        ActionBubble("Plan 7 jours")
                    }
                }
            }
        }
    }
}

@Composable
private fun ActionBubble(label: String) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.84f),
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.22f),
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