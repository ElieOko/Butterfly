package elieoko.hoshi.butterfly.view.navigation

sealed class Route(
    val label: String,
    val emoji: String,
) {
    data object Home : Route("Accueil", "🏠")
    data object Bible : Route("Bible", "📖")
    data object Plan : Route("Plans", "🗓️")
    data object Group : Route("Groupes", "🤝")
    data object Assistant : Route("Assistant", "✨")
    data object About : Route("A propos", "🦋")

    companion object {
        val mainItems: List<Route> = listOf(Home, Bible, Plan, Group, Assistant, About)
    }
}
