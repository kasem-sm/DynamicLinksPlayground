package kasem.sm.dynamiclinksplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import kasem.sm.dynamiclinksplayground.ui.navigation.Navigation
import kasem.sm.dynamiclinksplayground.ui.screen.ListPostScreen
import kasem.sm.dynamiclinksplayground.ui.screen.PostDetailScreen
import kasem.sm.dynamiclinksplayground.ui.theme.DynamicLinksPlaygroundTheme
import kasem.sm.dynamiclinksplayground.utils.Constants.TAG

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicLinksPlaygroundTheme {
                // TODO: 11/13/2021 remove google-services.json content
                val imageLoader = ImageLoader.Builder(this)
                    .availableMemoryPercentage(0.25)
                    .crossfade(250)
                    .build()

                val navController = rememberNavController()

                handleIncomingDeepLinks(navController = navController)

                NavHost(
                    navController = navController,
                    startDestination = Navigation.ListPostScreen.route
                ) {
                    composable(Navigation.ListPostScreen.route) {
                        ListPostScreen(imageLoader = imageLoader,
                            navController = navController
                        )
                    }
                    composable(
                        route = "${Navigation.PostDetailScreen.route}/{post_id}",
                        arguments = Navigation.PostDetailScreen.arguments,
                    ) { backStackEntry ->
                        val postId = backStackEntry.arguments?.getInt("post_id")
                        postId?.let {
                            PostDetailScreen(
                                postId = it,
                                imageLoader = imageLoader,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleIncomingDeepLinks(
        navController: NavController
    ) {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                val deepLink = pendingDynamicLinkData.link
                deepLink?.let { uri ->
                    val path = uri.toString().substring(deepLink.toString().lastIndexOf("/") + 1)

                    // In case if you have multiple shareable items such as User Post, User Profile,
                    // you can check if
                    // the uri contains the required string.
                    // In our case we will check if the path contains the string, 'post'

                    when {
                        uri.toString().contains("post") -> {
                            // In my case, the ID is an Integer
                            val postId = path.toInt()
                            // Call your API or DB to get the post with the ID [postId]
                            // and open the required screen here.
                            navController.navigate("${Navigation.PostDetailScreen.route}/$postId")
                        }
                    }
                }
            }.addOnFailureListener {
                // This lambda will be triggered when there is a failure.
                // Handle
                Log.d(TAG, "handleIncomingDeepLinks: ${it.message}")
            }
    }
}
