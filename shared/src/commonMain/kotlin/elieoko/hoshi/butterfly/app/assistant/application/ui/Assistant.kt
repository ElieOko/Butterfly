package elieoko.hoshi.butterfly.app.assistant.application.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.PillRow

@Composable
fun Assistant() {
    var expanded by remember { mutableStateOf(false) }

    ButterflyPage(
        title = "Assistant biblique",
        subtitle = "Pose tes questions, recois des explications simples et des orientations de priere.",
    ) {
        ConversationBubble(
            text = "Comment retrouver la paix quand je traverse une periode de stress ?",
            accent = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f),
        )
        ConversationBubble(
            text = "Lis Philippiens 4:6-7. Je peux te proposer une priere personnalisee et un plan de 7 jours.",
            accent = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
            expanded = expanded,
            onToggle = { expanded = !expanded },
        )

        AnimatedVisibility(visible = expanded) {
            PillRow(
                labels = listOf(
                    "Expliquer un verset",
                    "Resumer un chapitre",
                    "Suggere une priere",
                    "Plan adapte",
                ),
            )
        }

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            placeholder = { Text("Ecris ta question biblique ici...") },
            trailingIcon = { Text("➤") },
        )
    }
}

@Composable
private fun ConversationBubble(
    text: String,
    accent: Color,
    expanded: Boolean = false,
    onToggle: (() -> Unit)? = null,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        colors = CardDefaults.cardColors(containerColor = accent),
        shape = MaterialTheme.shapes.medium,
        onClick = { onToggle?.invoke() },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
            )
            if (onToggle != null) {
                Text(
                    if (expanded) "Masquer les actions" else "Voir les actions IA",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}
