package dev.yjyoon.novelist.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.yjyoon.novelist.ui.bookshelf.BookShelfDetail
import dev.yjyoon.novelist.ui.bookshelf.BookshelfViewModel
import dev.yjyoon.novelist.ui.cover.generation.GenerateCoverScreen
import dev.yjyoon.novelist.ui.oss.OssScreen
import dev.yjyoon.novelist.ui.title.TitleScreen

object NavDestination {
    const val TITLE = "title"
    const val COVER_GENERATION = "cover-generation"
    const val BOOKSHELF_DETAIL = "bookshelf-detail"
    const val OSS_DETAIL = "oss-detail"
}

@Composable
fun NavGraph(navController: NavHostController) {
    // Declare singleton view models here.
    val bookShelfViewModel: BookshelfViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = NavDestination.TITLE
    ) {
        composable(NavDestination.TITLE) {
            TitleScreen(navController = navController, bookShelfViewModel = bookShelfViewModel)
        }
        composable(NavDestination.COVER_GENERATION) {
            GenerateCoverScreen(navController = navController, viewModel = hiltViewModel())
        }
        composable(NavDestination.BOOKSHELF_DETAIL) {
            BookShelfDetail(navController = navController, viewModel = bookShelfViewModel)
        }
        composable(NavDestination.OSS_DETAIL) {
            OssScreen()
        }
    }
}

