package tw.eita.mrvn

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object CraftScreen : Screen("craft_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}