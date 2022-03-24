package dev.yjyoon.coverist.ui.bookshelf

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.yjyoon.coverist.ui.common.CommonLoading

@Composable
fun BookShelfRow(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: BookShelfViewModel
) {
    val bookshelf by viewModel.bookshelf.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "내 책장",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 18.dp, top = 18.dp, end = 18.dp, bottom = 12.dp)
        )
        if (bookshelf is BookShelfState.Loading)
            CommonLoading(modifier = Modifier.fillMaxSize())
        else if (bookshelf is BookShelfState.Empty)
            EmptyBook(modifier = Modifier.fillMaxSize())
        else if (bookshelf is BookShelfState.Success) {
            (bookshelf as BookShelfState.Success).data.let {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                    contentPadding = PaddingValues(horizontal = 18.dp)
                ) {
                    items(it.size) { index ->
                        Card(
                            shape = RoundedCornerShape(6.dp),
                            elevation = 5.dp
                        ) {
                            AsyncImage(
                                model = it[index].coverUrls[0],
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(144.dp)
                                    .aspectRatio(2 / 3f)
                                    .clickable {
                                        viewModel.selectBook(it[index])
                                        navController.navigate("bookshelf-detail")
                                    },
                            )
                        }
                    }
                }
            }
        } else {
            //TODO: Bookshelf.Error
        }
    }
}

@Composable
fun EmptyBook(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            "아직 아무런 책이 없어요!",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}