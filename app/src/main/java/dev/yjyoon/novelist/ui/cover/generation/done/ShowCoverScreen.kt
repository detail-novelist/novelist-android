package dev.yjyoon.novelist.ui.cover.generation.done

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.yjyoon.novelist.ui.common.HorizontalImageCarousel
import dev.yjyoon.novelist.ui.theme.CoveristTheme

@Composable
fun ShowCoverScreen(
    navController: NavController,
    coverUrls: List<String>,
    onConfirm: () -> Unit
) {

    BackHandler {
        navController.navigate("title") {
            popUpTo("title") { inclusive = true }
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        bottomBar = {
            ConfirmButton(onClick = onConfirm)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            ShowCoverTitle()
            ShowCoverContent(coverUrls)
        }
    }
}

@Composable
fun ShowCoverTitle(modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = Color.Black.copy(alpha = 0.12f),
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        Text(
            "새로운 표지가 탄생했어요!",
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 18.dp)
        )
    }
}

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.primary
            ),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("완료", style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun ShowCoverContent(
    coverUrls: List<String>,
    modifier: Modifier = Modifier
) {
    HorizontalImageCarousel(
        imageUrls = coverUrls,
        aspectRatio = 2 / 3f
    )
}

@Composable
fun CoverImage(
    text: String
) {
    Surface(
        color = Color.Yellow
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text)
        }
    }
}

@Preview
@Composable
fun ShowCoverScreenPreview() {
    CoveristTheme {
    }
}