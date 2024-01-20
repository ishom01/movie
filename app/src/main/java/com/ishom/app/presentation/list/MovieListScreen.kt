package com.ishom.app.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ishom.app.presentation.ui.template.widget.movie.MovieHorizontalList
import com.ishom.core.R
import com.ishom.movie.data.source.Resource
import com.ishom.movie.domain.model.Movie

@Composable
fun MovieListScreen(
    popularMovieState: State<Resource<List<Movie>>?>,
    nowPlayingMovieState: State<Resource<List<Movie>>?>,
    upcomingMovieState: State<Resource<List<Movie>>?>,
    onMovieClicked: ((id: Int) -> Unit)
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        MovieHorizontalList(
            title = stringResource(id = R.string.now_playing),
            scale = 1.5f,
            state = nowPlayingMovieState,
            onMovieClicked = onMovieClicked
        )
        MovieHorizontalList(
            title = stringResource(id = R.string.popular),
            state = popularMovieState,
            onMovieClicked = onMovieClicked
        )
        MovieHorizontalList(
            title = stringResource(id = R.string.upcoming),
            state = upcomingMovieState,
            onMovieClicked = onMovieClicked
        )
        Box(modifier = Modifier.height(58.dp))
    }
}