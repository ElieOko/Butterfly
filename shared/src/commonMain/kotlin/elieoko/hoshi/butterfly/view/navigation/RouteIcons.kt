package elieoko.hoshi.butterfly.view.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoStories
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Spa
import androidx.compose.ui.graphics.vector.ImageVector

val Route.icon: ImageVector
  get() = when (this) {
    Route.Home -> Icons.Rounded.Home
    Route.Bible -> Icons.Rounded.AutoStories
    Route.Notes -> Icons.Rounded.EditNote
    Route.Meditation -> Icons.Rounded.Spa
    Route.Groups -> Icons.Rounded.Groups
    Route.Account -> Icons.Rounded.Person
  }
