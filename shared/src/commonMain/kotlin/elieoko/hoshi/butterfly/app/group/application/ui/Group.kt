package elieoko.hoshi.butterfly.app.group.application.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import elieoko.hoshi.butterfly.core.ui.components.ButterflyBadge
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.OrganicCard

private data class ShareGroup(
    val id: String,
    val name: String,
    val description: String,
)

@Composable
fun Group() {
    var inviteCode by remember { mutableStateOf("") }
    val joinedIds = remember { mutableStateListOf<String>() }
    val groups = remember {
        listOf(
            ShareGroup("couple-hope", "Couples d esperance", "Priere en duo et progression commune."),
            ShareGroup("young-adults", "Jeunes adultes en foi", "Partage quotidien et etude biblique."),
            ShareGroup("family-altars", "Autels de famille", "Parents, enfants et devotion en maison."),
        )
    }

    ButterflyPage(
        title = "Groupes de partage",
        subtitle = "Rejoins des groupes spirituels, commente les passages et prie en communaute.",
    ) {
        OrganicCard(
            title = "Entrer avec un code",
            subtitle = "Ajoute un code d invitation pour rejoindre un nouveau groupe.",
        ) {
            OutlinedTextField(
                value = inviteCode,
                onValueChange = { inviteCode = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: HOPE-2026") },
                singleLine = true,
            )
            Button(
                onClick = {
                    if (inviteCode.isNotBlank()) {
                        val customId = "custom-${inviteCode.lowercase()}"
                        if (customId !in joinedIds) joinedIds += customId
                        inviteCode = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Rejoindre avec ce code")
            }
        }

        groups.forEachIndexed { index, group ->
            val isJoined = group.id in joinedIds
            OrganicCard(
                title = group.name,
                subtitle = group.description,
                accentA = if (index % 2 == 0) Color(0xFF2A3F87) else Color(0xFF6B4EAF),
                accentB = if (index % 2 == 0) Color(0xFF4964BC) else Color(0xFF8E69C4),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    ButterflyBadge(if (isJoined) "Membre" else "Ouvert")
                    Text(
                        text = if (isJoined) "Tu participes deja" else "8 a 24 membres actifs",
                        color = Color.White.copy(alpha = 0.92f),
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Button(
                    onClick = { if (!isJoined) joinedIds += group.id },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isJoined,
                ) {
                    Text(if (isJoined) "Groupe rejoint" else "Entrer dans ce groupe")
                }
            }
        }
    }
}
