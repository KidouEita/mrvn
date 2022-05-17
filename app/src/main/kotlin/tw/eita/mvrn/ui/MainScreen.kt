package tw.eita.mvrn.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MainScreen() {
    Box {
        Image(
            painter = rememberAsyncImagePainter("https:\\/\\/apexlegendsstatus.com\\/assets\\/maps\\/Storm_Point.png"),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow {
                items(5) { NewsCard() }
            }
        }
    }
}

@Composable
fun NewsCard() {
    Card(
        modifier = Modifier
            .width(400.dp)
            .height(250.dp)
            .padding(10.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                // todo img from api
                model = "https:\\/\\/media.contentapi.ea.com\\/content\\/dam\\/apex-legends\\/images\\/2022\\/05\\/apex-media-news-saviors-patch-keyart.jpg.adapt.crop16x9.431p.jpg"
            ),
            contentDescription = "News",
            contentScale = ContentScale.Crop
        )
    }
}