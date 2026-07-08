package elieoko.hoshi.butterfly.app.bible.application.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.PillRow

@Composable
fun Bible() {
    ButterflyPage(
        title = "Lecture de la Bible",
        subtitle = "Lis, comprends et sauvegarde tes passages avec un rendu uniforme.",
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            placeholder = { Text("Recherche rapide: foi, pardon, esperance...") },
        )

        ScriptureItem(
            chapter = "Jean 14:27",
            verse = "Je vous laisse la paix, je vous donne ma paix...",
            explanation = "Jesus promet une paix stable, differente de celle du monde.",
        )
        ScriptureItem(
            chapter = "Philippiens 4:6-7",
            verse = "Ne vous inquietez de rien; mais en toute chose faites connaitre vos besoins a Dieu...",
            explanation = "La paix grandit quand la priere remplace l'inquietude.",
        )

        PillRow(
            labels = listOf(
                "Audio",
                "Surligner",
                "Notes",
                "Favoris",
                "Partager",
            ),
        )
    }
}

@Composable
private fun ScriptureItem(
    chapter: String,
    verse: String,
    explanation: String,
) {
    var expanded by remember { mutableStateOf(false) }
    val borderAlpha by animateFloatAsState(
        targetValue = if (expanded) 0.72f else 0.22f,
        animationSpec = tween(180),
        label = "scripture-border",
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = borderAlpha),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 14.dp, bottomStart = 14.dp, bottomEnd = 24.dp),
            )
            .background(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 14.dp, bottomStart = 14.dp, bottomEnd = 24.dp),
            )
            .clickable { expanded = !expanded }
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(chapter, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
        Text(verse, style = MaterialTheme.typography.bodyLarge)
        AnimatedVisibility(expanded) {
            Text(
                "Explication: $explanation",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
