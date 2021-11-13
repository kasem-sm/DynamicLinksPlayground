package kasem.sm.dynamiclinksplayground.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import kasem.sm.dynamiclinksplayground.domain.model.Post

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    post: Post,
    onShareButtonClicked: () -> Unit,
    onPostClicked: ((Int) -> Unit?)? = null,
) {
    Card(
        modifier = modifier
            .height(300.dp)
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .clickable {
                    onPostClicked?.invoke(post.id)
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = post.title,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .padding(10.dp),
                    color = MaterialTheme.colors.onBackground
                )
                IconButton(onClick = onShareButtonClicked) {
                    Icon(imageVector = Icons.Default.Share,
                        contentDescription = Icons.Default.Share.name,
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }

            imageLoader.Image(
                modifier = Modifier
                    .fillMaxSize(),
                data = post.imageLink
            )
        }
    }
}