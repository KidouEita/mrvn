package tw.eita.mrvn.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import tw.eita.mrvn.R
import tw.eita.mrvn.Screen
import tw.eita.mrvn.data.News
import tw.eita.mrvn.toReadableTime

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {

    val newsList by viewModel.news.observeAsState(listOf())
    val map by viewModel.map.observeAsState()
    val isRefresh by viewModel.isRefresh.observeAsState(false)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter(map?.current?.asset),
            contentDescription = map?.current?.map,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        SwipeRefresh(
            modifier = Modifier.fillMaxSize(),
            onRefresh = { viewModel.refresh() },
            state = rememberSwipeRefreshState(isRefresh)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item { NewsListView(newsList = newsList) }
                item {
                    Text(
                        text = "Current map is ${map?.current?.map}\n" +
                                "End at ${map?.current?.endMillis?.toReadableTime()}\n" +
                                "Next map is ${map?.next?.map}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                item {
                    Selection(buttonText = stringResource(id = R.string.craft_rotation)) {
                        navController.navigate(Screen.CraftScreen.route)
                    }
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
            .width(350.dp)
            .wrapContentHeight()
            .padding(10.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = news.img),
                contentDescription = news.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(350.dp, 200.dp)
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