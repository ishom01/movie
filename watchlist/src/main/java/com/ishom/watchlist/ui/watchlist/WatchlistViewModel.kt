package com.ishom.watchlist.ui.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ishom.movie.domain.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    movieUseCase: MovieUseCase
): ViewModel() {

    private val _watchList = movieUseCase.getWatchList().asLiveData()
    val watchList get() = _watchList
}