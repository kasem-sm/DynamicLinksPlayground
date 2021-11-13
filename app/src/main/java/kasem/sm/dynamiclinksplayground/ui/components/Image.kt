package kasem.sm.dynamiclinksplayground.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageLoader.Image(
    modifier: Modifier = Modifier,
    data: String,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val painter = rememberImagePainter(
        data = data,
        imageLoader = this
    )

    Box(modifier = modifier
        .size(200.dp)) {
        when (painter.state) {
            is ImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = MaterialTheme.colors.primary,
                    strokeWidth = 1.dp
                )
            }
            else -> Unit
        }

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}
