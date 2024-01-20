package com.ishom.app.presentation.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishom.app.presentation.UiState
import com.ishom.app.presentation.util.toUiState
import com.ishom.movie.domain.MovieUseCase
import com.ishom.movie.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    movieUseCase: MovieUseCase
): ViewModel() {

    private val _watchList = MutableStateFlow<UiState<List<Movie>>>(UiState.default())
    val watchList get() = _watchList

    init {
        movieUseCase.getWatchList().onEach {
            _watchList.value = it.toUiState()
        }.launchIn(viewModelScope)
    }
}