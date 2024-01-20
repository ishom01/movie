package com.ishom.app.presentation.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishom.app.presentation.UiState
import com.ishom.app.presentation.util.toUiState
import com.ishom.movie.domain.FavoriteUseCase
import com.ishom.movie.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    favoriteUseCase: FavoriteUseCase
): ViewModel() {

    private val _favoriteState = MutableStateFlow<UiState<List<Movie>>>(UiState.default())
    val favoriteState get() = _favoriteState

    init {
        favoriteUseCase.all().onEach {
            _favoriteState.value = it.toUiState()
        }.launchIn(viewModelScope)
    }
}