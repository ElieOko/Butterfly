package elieoko.hoshi.butterfly.app.about.application.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.butterfly
import butterfly.shared.generated.resources.pray
import elieoko.hoshi.butterfly.core.ui.components.ButterflyPage
import elieoko.hoshi.butterfly.core.ui.components.OrganicCard
import org.jetbrains.compose.resources.painterResource

@Composable
fun About() {
    ButterflyPage(
        title = "A propos de Butterfly",
        subtitle = "Une plateforme chretienne moderne pour la croissance spirituelle personnelle et communautaire.",
    ) {
        OrganicCard(
            title = "Notre mission",
            subtitle = "Aider chaque personne a vivre une relation vivante avec Dieu au quotidien.",
            accentA = Color(0xFF2A3E88),
            accentB = Color(0xFF5D70D0),
        )
        OrganicCard(
            title = "Notre vision",
            subtitle = "Creer une experience spirituelle premium, intelligente, accessible et durable.",
            accentA = Color(0xFF6F52B4),
            accentB = Color(0xFF9571CD),
        )
        OrganicCard(
            title = "Nos piliers",
            subtitle = "Bible, Priere, Meditation, Communaute, Accompagnement IA.",
            accentA = Color(0xFF986D2C),
            accentB = Color(0xFFC79A4C),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("Version", color = Color.White, style = MaterialTheme.typography.bodySmall)
                Text("1.0.0-preview", color = Color.White, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column {
                Image(
                    painter = painterResource(Res.drawable.butterfly),
                    contentDescription = "Butterfly visuel",
                    modifier = Modifier.size(88.dp),
                )
            }
            Column {
                Image(
                    painter = painterResource(Res.drawable.pray),
                    contentDescription = "Priere visuel",
                    modifier = Modifier.size(88.dp),
                )
            }
        }
    }
}
