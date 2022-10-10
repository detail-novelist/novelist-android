package dev.yjyoon.novelist.ui.bookshelf

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import dev.yjyoon.novelist.ui.common.CommonLoading

@Composable
fun BookShelfRow(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: BookshelfViewModel
) {
    val bookshelf by viewModel.bookshelf.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "내 소설",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 18.dp, top = 18.dp, end = 18.dp, bottom = 12.dp)
        )
        if (bookshelf is BookshelfState.Loading)
            CommonLoading(modifier = Modifier.fillMaxSize())
        else if (bookshelf is BookshelfState.Empty)
            EmptyBook(modifier = Modifier.fillMaxSize())
        else if (bookshelf is BookshelfState.Success) {
            (bookshelf as BookshelfState.Success).data.let {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                    contentPadding = PaddingValues(horizontal = 18.dp)
                ) {
                    items(it.size) { index ->
                        Card(
                            shape = RoundedCornerShape(6.dp),
                            elevation = 5.dp
                        ) {
                            GlideImage(
                                imageModel = it[index].coverUrls[0],
                                contentScale = ContentScale.Crop,
                                shimmerParams = ShimmerParams(
                                    baseColor = MaterialTheme.colors.surface,
                                    highlightColor = Color.Gray,
                                    durationMillis = 350,
                                    dropOff = 0.65f,
                                    tilt = 20f
                                ),
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
            "아직 아무런 소설이 없어요!",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}