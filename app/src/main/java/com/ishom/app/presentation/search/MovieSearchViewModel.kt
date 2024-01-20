package com.ishom.app.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishom.app.presentation.UiState
import com.ishom.app.presentation.util.toUiState
import com.ishom.movie.domain.MovieUseCase
import com.ishom.movie.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val useCase: MovieUseCase
): ViewModel() {

    private var _job: Job? = null

    private var _state = MutableStateFlow(MovieSearchUiState())
    val state get() = _state

    fun onSearchKey(key: String) {
        _state.update {
            it.copy(searchKey = key)
        }
        if (_state.value.searchKey.isEmpty()) {
            _state.update {
                it.copy(moviesState = UiState.default())
            }
            return
        }
        _job?.cancel()
        _job = viewModelScope.launch {
            _state.update {
                it.copy(moviesState = UiState.default<List<Movie>>().copy(isLoading = true))
            }
            delay(500)
            useCase.getSearchMovie(key).collect { resource ->
                _state.update {
                    it.copy(moviesState = resource.toUiState())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _job?.cancel()
    }
}