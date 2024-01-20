package com.ishom.app.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ishom.movie.domain.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    movieUseCase: MovieUseCase,
) : ViewModel() {

    private val _nowMovies = movieUseCase.getNowPlaying().asLiveData()
    val nowMovies get() = _nowMovies

    private val _popularMovies = movieUseCase.getPopular().asLiveData()
    val popularMovies get() = _popularMovies

    private val _upcomingMovies = movieUseCase.getUpcoming().asLiveData()
    val upcomingMovies get() = _upcomingMovies
}