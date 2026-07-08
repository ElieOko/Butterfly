package elieoko.hoshi.butterfly.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import butterfly.shared.generated.resources.Res
import butterfly.shared.generated.resources.bible
import butterfly.shared.generated.resources.books
import butterfly.shared.generated.resources.butterfly
import org.jetbrains.compose.resources.painterResource

/** Kept for compatibility; immersive backgrounds are preferred. */
@Composable
fun ButterflyBackground(modifier: Modifier = Modifier) {
    ImmersiveBackground(image = Res.drawable.butterfly, modifier = modifier)
}

@Composable
@Preview(showBackground = true)
fun MCard() {
    Column(
        modifier = Modifier.background(Color.Black.copy(0.9f)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(Res.drawable.bible),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.White,
        )
        Spacer(Modifier.height(2.dp))
        Text("Verse of the day", color = Color.White.copy(0.8f))
        Spacer(Modifier.height(7.dp))
        Text(
            text = "I am the true vine, and my Father is the husbandman.",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
        SuggestionChip(
            colors = SuggestionChipDefaults.suggestionChipColors(containerColor = Color.Black),
            elevation = SuggestionChipDefaults.suggestionChipElevation(5.dp),
            label = { Text("John 15:1", color = Color.White) },
            onClick = {},
            border = BorderStroke(3.dp, Color.Black),
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.books),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color.White,
                )
            },
        )
    }
}
