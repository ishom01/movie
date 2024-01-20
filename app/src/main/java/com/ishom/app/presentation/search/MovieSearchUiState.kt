package com.ishom.app.presentation.search

import com.ishom.app.presentation.UiState
import com.ishom.movie.domain.model.Movie

data class MovieSearchUiState(
    val moviesState: UiState<List<Movie>> = UiState.default(),
    val searchKey: String = "",
)
