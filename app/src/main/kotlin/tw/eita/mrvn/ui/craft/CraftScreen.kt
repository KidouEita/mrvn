package tw.eita.mrvn.ui.craft

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import tw.eita.mrvn.data.Craft

@Composable
fun CraftScreen(
    navController: NavController,
    viewModel: CraftViewModel = viewModel()
) {
    val craftList by viewModel.craft.observeAsState(listOf())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Rounded.ArrowBack, "back")
            }
        }
        items(craftList) { craftBundle ->
            CraftItem(craftBundle.bundleType, craftBundle.bundleContent)
        }

    }
}

@Composable
fun CraftItem(
    swapCycle: String,
    list: List<Craft> = listOf()
) {
    Column {
        Text(text = swapCycle, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(5.dp)
        ) {
            items(list) { craft ->
                Column(
                    modifier = Modifier
                        .width(150.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(craft.itemType.rarityColor))
                        .padding(10.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = craft.itemType.asset),
                        contentDescription = craft.itemType.name,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(15.dp))
                    )
                    Text(text = craft.itemType.name)
                    Text(text = "$${craft.cost}")
                }
            }
        }
    }
}