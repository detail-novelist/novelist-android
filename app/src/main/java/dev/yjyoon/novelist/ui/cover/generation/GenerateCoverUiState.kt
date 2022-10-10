package dev.yjyoon.novelist.ui.cover.generation

sealed class GenerateCoverUiState {
    object Waiting : GenerateCoverUiState()
    object Generating : GenerateCoverUiState()
    object Done : GenerateCoverUiState()
    object Fail : GenerateCoverUiState()
}