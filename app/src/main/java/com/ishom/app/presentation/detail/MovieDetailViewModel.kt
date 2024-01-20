package com.ishom.app.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishom.movie.data.source.Resource
import com.ishom.movie.domain.MovieUseCase
import com.ishom.movie.domain.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
): ViewModel() {

    private var _movieDetail = MutableStateFlow<Resource<MovieDetail>>(Resource.Loading())
    val movieDetail get() = _movieDetail

    fun getDetail(id: Int) {
        if (id == -1) {
            _movieDetail.update { Resource.Error("Movie id cannot be empty") }
            return
        }
        movieUseCase.getDetail(id).onEach { movieDetail ->
            _movieDetail.update { movieDetail }
        }.launchIn(viewModelScope)
    }

    fun setWatchlist() {
        viewModelScope.launch {
            var movie = _movieDetail.value.data ?: return@launch
            movie = movie.copy(isWatchList = !movie.isWatchList)
            movieUseCase.checkFavorite(movie)
            _movieDetail.update {
                Resource.Success(movie)
            }
        }
    }
}