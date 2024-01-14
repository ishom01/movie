package com.ishom.app.ui.movie.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishom.movie.domain.MovieUseCase
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val watchlistUseCase: MovieUseCase,
): ViewModel() {

    private var _movieDetail = MutableLiveData<Resource<MovieDetail>>(Resource.Loading())
    val movieDetail get() = _movieDetail

    fun getDetail(id: Int) {
        viewModelScope.launch {
            movieUseCase.getDetail(id).collect {
                _movieDetail.value = it
            }
        }
    }
    suspend fun setWatchlist(detail: MovieDetail) {
        viewModelScope.launch {
            val movie = _movieDetail.value?.data ?: return@launch
            movie.isWatchList = !movie.isWatchList
            if (movie.isWatchList)
                movieUseCase.addFavorite(detail)
            else
                movieUseCase.renameFavorite(detail.id)
            _movieDetail.value = Resource.Success(movie)
        }
    }
}