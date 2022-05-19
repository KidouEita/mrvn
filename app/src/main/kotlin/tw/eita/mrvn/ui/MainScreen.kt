package tw.eita.mrvn.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import tw.eita.mrvn.R

@Composable
fun MainScreen() {
    val context = LocalContext.current
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
            stringArrayResource(id = R.array.main_selection).forEach { selectionString ->
                Selection(selectionString) {
                    Toast.makeText(context, selectionString, Toast.LENGTH_SHORT).show()
                }
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

@Composable
fun Selection(
    buttonText: String,
    onClick: (() -> Unit)? = null
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick?.invoke() }
    ) {
        Text(
            text = buttonText,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}