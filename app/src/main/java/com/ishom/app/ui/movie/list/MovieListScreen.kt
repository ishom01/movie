package com.ishom.app.ui.movie.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ishom.app.template.widget.movie.MovieHorizontalList

@Composable
fun MovieListScreen(
    controller: NavHostController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        MovieHorizontalList(
            controller,
            title = "Now Playing",
            scale = 1.5f,
            state = viewModel.nowMovies.observeAsState()
        )
        MovieHorizontalList(
            controller,
            title = "Popular",
            state = viewModel.popularMovies.observeAsState()
        )
        MovieHorizontalList(
            controller,
            title = "Upcoming",
            state = viewModel.upcomingMovies.observeAsState()
        )
        Box(modifier = Modifier.height(58.dp))
    }
}