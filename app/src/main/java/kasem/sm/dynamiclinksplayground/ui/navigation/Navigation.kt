package kasem.sm.dynamiclinksplayground.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Navigation(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
) {
    object ListPostScreen : Navigation(
        route = "list_post_screen"
    )

    object PostDetailScreen : Navigation(
        route = "post_detail_screen",
        arguments = listOf(navArgument(
            name = "post_id",
            builder = {
                type = NavType.IntType
            }
        ))
    )
}
