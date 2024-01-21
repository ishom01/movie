package com.ishom.favorite.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoriteScreen(
    onMovieClicked: ((id: Int) -> Unit),
) {

    val viewModel: FavoriteViewModel = hiltViewModel()

    FavoriteUI(
        onMovieClicked = onMovieClicked,
        movieState = viewModel.favoriteState.collectAsStateWithLifecycle(),
    )
}