package ints.devotion.navigator

import androidx.navigation.NamedNavArgument

sealed class Screen(
    private val baseRoute: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    val route: String = baseRoute.appendArguments(navArguments)

    object MapScreen : Screen("map_screen")
}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
    val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
        .orEmpty()
    val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
        .orEmpty()
    return "$this$mandatoryArguments$optionalArguments"
}