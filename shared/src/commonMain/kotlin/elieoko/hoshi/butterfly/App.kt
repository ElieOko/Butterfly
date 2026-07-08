package elieoko.hoshi.butterfly

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import elieoko.hoshi.butterfly.design.SpiritualTheme

private enum class AppDestination(val title: String, val emoji: String) {
    Home("Accueil", "🏠"),
    Bible("Bible", "📖"),
    Plans("Plans", "🗓️"),
    Ai("Assistant", "✨"),
}

@Composable
@Preview
fun App() {
    SpiritualTheme {
        var selectedDestination by rememberSaveable { mutableStateOf(AppDestination.Home) }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.navigationBarsPadding(),
                    containerColor = MaterialTheme.colorScheme.surface,
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
            AnimatedContent(
                targetState = selectedDestination,
                transitionSpec = {
                    fadeIn(animationSpec = spring()) with fadeOut(animationSpec = spring())
                },
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
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

@Composable
private fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Bonjour Elie", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold)
            Text(
                text = "Continuons ton parcours avec paix et constance.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        item {
            GradientCard(
                title = "Verset du jour",
                body = "\"Ta parole est une lampe à mes pieds, et une lumière sur mon sentier.\"",
                footer = "Psaume 119:105",
            )
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                QuickMetricCard(
                    title = "Série",
                    value = "14 jours",
                    modifier = Modifier.weight(1f),
                )
                QuickMetricCard(
                    title = "Objectif semaine",
                    value = "4 / 5 lectures",
                    modifier = Modifier.weight(1f),
                )
            }
        }
        item {
            SpiritualCard(title = "Continuer ton plan", subtitle = "Couple: Bâtir l'unité spirituelle") {
                LinearProgressIndicator(
                    progress = { 0.62f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Jour 13 sur 21", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {}) { Text("Reprendre") }
            }
        }
        item {
            SpiritualCard(title = "Prière guidée", subtitle = "Prendre 5 minutes de silence") {
                Text(
                    "Seigneur, aide-moi à marcher dans l'amour, la patience et la vérité aujourd'hui.",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(onClick = {}) { Text("Démarrer") }
            }
        }
        item { Spacer(modifier = Modifier.height(12.dp)) }
    }
}

@Composable
private fun BibleScreen() {
    val verses = listOf(
        "1 Au commencement, Dieu créa les cieux et la terre.",
        "2 La terre était informe et vide...",
        "3 Dieu dit: Que la lumière soit! Et la lumière fut.",
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Text("Bible", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold)
        }
        item {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                label = { Text("Recherche ultra rapide") },
                placeholder = { Text("Ex: amour, foi, espérance") },
            )
        }
        item {
            SpiritualCard(title = "Genèse 1", subtitle = "Version: Louis Segond") {
                verses.forEachIndexed { index, verse ->
                    val isHighlighted = index == 2
                    Text(
                        text = verse,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isHighlighted) MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f) else Color.Transparent)
                            .padding(8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    PillAction("Audio")
                    PillAction("Note")
                    PillAction("Favori")
                    PillAction("Partager")
                }
            }
        }
    }
}

@Composable
private fun PlansScreen() {
    val plans = listOf(
        "Couples: Grandir ensemble (21 jours)" to 0.45f,
        "Prière et intimité avec Dieu (30 jours)" to 0.2f,
        "Guérison intérieure (14 jours)" to 0.8f,
        "Espérance dans l'épreuve (7 jours)" to 0.0f,
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Text("Plans de lecture", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold)
            Text(
                "Sélectionnés selon ton profil et ton rythme.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        items(plans) { (title, progress) ->
            SpiritualCard(
                title = title,
                subtitle = if (progress == 0f) "Nouveau programme" else "Progression en cours",
            ) {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text("${(progress * 100).toInt()}% complété", style = MaterialTheme.typography.bodySmall)
                    Text("Voir détails", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Composable
private fun AssistantScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Text("Assistant biblique IA", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold)
            Text(
                "Explications simples, prières personnalisées et recommandations.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        item {
            SpiritualCard(title = "Question", subtitle = "Que dit la Bible sur la paix intérieure ?") {
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "La Bible présente la paix comme un fruit de la confiance en Dieu. "
                        + "Commence avec Philippiens 4:6-7 et Jean 14:27.",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PillAction("Expliquer verset")
                    PillAction("Prière")
                    PillAction("Plan adapté")
                }
            }
        }
        item {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                placeholder = { Text("Pose ta question biblique...") },
                trailingIcon = { Text("➡️") },
            )
        }
    }
}

@Composable
private fun GradientCard(
    title: String,
    body: String,
    footer: String,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.92f),
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.87f),
                        ),
                    ),
                )
                .padding(20.dp),
        ) {
            Text(title, color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(10.dp))
            Text(body, color = Color.White, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(footer, color = Color.White.copy(alpha = 0.85f), style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun SpiritualCard(
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(2.dp))
            content()
        }
    }
}

@Composable
private fun QuickMetricCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun PillAction(label: String) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f), CircleShape)
            .clickable {}
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, style = MaterialTheme.typography.labelMedium, textAlign = TextAlign.Center)
    }
}