package kasem.sm.dynamiclinksplayground.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.ImageLoader
import kasem.sm.dynamiclinksplayground.datasource.FakeDataSource
import kasem.sm.dynamiclinksplayground.ui.components.PostItem
import kasem.sm.dynamiclinksplayground.utils.Constants
import kasem.sm.dynamiclinksplayground.utils.generateSharingLink
import kasem.sm.dynamiclinksplayground.utils.shareDeepLink

@Composable
fun PostDetailScreen(
    postId: Int,
    imageLoader: ImageLoader,
) {
    val context = LocalContext.current

    val post = FakeDataSource.listOfPost.find {
        it.id == postId
    }

    Column(
        modifier = androidx.compose.ui.Modifier
            .padding(10.dp)
    ) {
        post?.let {
            PostItem(
                imageLoader = imageLoader, post = it,
                onShareButtonClicked = {
                    generateSharingLink(
                        deepLink = "${Constants.PREFIX}/posts/${post.id}".toUri(),
                        previewImageLink = post.imageLink.toUri(),
                        getShareableLink = { generatedLink ->
                            context.shareDeepLink(
                                deepLink = generatedLink
                            )
                        }
                    )
                },
            )
        }
    }
}
