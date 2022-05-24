package tw.eita.mrvn.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import tw.eita.mrvn.R
import tw.eita.mrvn.data.News

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {

    val newsList by viewModel.news.observeAsState(listOf())
    val map by viewModel.map.observeAsState()
    val context = LocalContext.current

    Box {
        Image(
            painter = rememberAsyncImagePainter(map?.current?.asset),
            contentDescription = map?.current?.map,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            NewsListView(newsList = newsList)
            Text(text = "Current map is ${map?.current?.map}")
            stringArrayResource(id = R.array.main_selection).forEach { selectionString ->
                Selection(selectionString) {
                    Toast.makeText(context, selectionString, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun NewsListView(newsList: List<News>) {
    LazyRow {
        items(newsList) { news ->
            NewsCard(news)
        }
    }
}

@Composable
fun NewsCard(news: News) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .wrapContentHeight()
            .padding(10.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = news.img),
                contentDescription = news.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = news.title ?: "no title",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(10.dp)
            )
        }
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