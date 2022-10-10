package dev.yjyoon.novelist.ui.title

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.yjyoon.novelist.R
import dev.yjyoon.novelist.ui.bookshelf.BookShelfRow
import dev.yjyoon.novelist.ui.bookshelf.BookshelfViewModel
import kotlinx.coroutines.launch

@Composable
fun TitleScreen(
    navController: NavController,
    bookShelfViewModel: BookshelfViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            TitleDrawer(
                navController = navController,
                isOpen = scaffoldState.drawerState.isOpen,
                onBack = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
        },
        drawerShape = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        ),
        bottomBar = {
            StartButton(
                onClick = {
                    navController.navigate("cover-generation")
                },
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding)
        ) {
            TitleGraphic(
                configuration = configuration,
                onMenuClick = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                })
            BookShelfRow(navController = navController, viewModel = bookShelfViewModel)
        }
    }
}

@Composable
fun TitleGraphic(
    modifier: Modifier = Modifier,
    configuration: Configuration,
    onMenuClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(configuration.screenWidthDp.dp)
            .background(color = MaterialTheme.colors.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.coverist_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .blur(12.dp)
        )
        Text(
            "내가 상상하던 소설,\n이젠 쉽고 간편하게",
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.5f),
                    offset = Offset(4f, 4f),
                    blurRadius = 8f
                )
            ),
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(18.dp)
        )
        IconButton(
            onClick = onMenuClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(6.dp)
        ) {
            Icon(
                Icons.Rounded.Menu,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun StartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 14.dp, horizontal = 20.dp),
        modifier = Modifier
            .padding(18.dp)
            .fillMaxWidth()
    ) {
        Text(
            "새로운 소설 만들기",
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
        )
    }

}

@Preview
@Composable
fun TitleScreenPreview() {
}