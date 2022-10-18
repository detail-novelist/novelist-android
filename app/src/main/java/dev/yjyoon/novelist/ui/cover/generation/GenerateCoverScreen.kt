package dev.yjyoon.novelist.ui.cover.generation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.yjyoon.novelist.ui.cover.generation.done.ShowCoverScreen
import dev.yjyoon.novelist.ui.cover.generation.input.BookInfoInputScreen
import dev.yjyoon.novelist.ui.cover.generation.loading.GeneratingScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GenerateCoverScreen(
    navController: NavController,
    viewModel: GenerateCoverViewModel
) {
    Crossfade(
        targetState = viewModel.uiState,
        animationSpec = tween(
            durationMillis = 100,
            easing = LinearEasing
        )
    ) { targetUiState ->
        when (targetUiState) {
            GenerateCoverUiState.Waiting -> {
                BookInfoInputScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            GenerateCoverUiState.Generating -> {
                GeneratingScreen()
            }
            GenerateCoverUiState.Done -> {
                ShowCoverScreen(
                    novel = viewModel.novelResult
                )
            }
            GenerateCoverUiState.Fail -> {
                //TODO: Fail to generate.
            }
        }
    }

}