package dev.yjyoon.novelist.ui.cover.generation.done

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShowCoverScreen(
    novel: String
) {
    Scaffold() { innerPadding ->
        Text(text = novel, modifier = Modifier.padding(innerPadding))
    }
}
