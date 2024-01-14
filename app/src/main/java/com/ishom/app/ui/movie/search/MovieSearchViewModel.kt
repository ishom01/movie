package com.ishom.app.ui.movie.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishom.movie.domain.MovieUseCase
import com.ishom.movie.domain.model.Movie
import com.ishom.movie.data.source.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val useCase: MovieUseCase
): ViewModel() {

    private var _job: Job? = null

    private var _searchKey = MutableStateFlow("")
    val searchKey get() = _searchKey

    private var _movies = MutableLiveData<Resource<List<Movie>>>(Resource.Success(listOf()))
    val movies get() = _movies

    fun onSearchKey(key: String) {
        _searchKey.value = key
        if (_searchKey.value.isEmpty()) {
            _movies.value = Resource.Success(listOf())
            return
        }
        _job?.cancel()
        _job = viewModelScope.launch {
            _movies.value = Resource.Loading()
            delay(500)
            useCase.getSearchMovie(query = key).collect {
                _movies.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _job?.cancel()
    }
}