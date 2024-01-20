package com.ishom.app.presentation.watchlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import com.ishom.app.presentation.UiState
import com.ishom.app.presentation.ui.template.widget.movie.MovieVerticalList
import com.ishom.app.presentation.ui.template.widget.movie.PageType
import com.ishom.movie.domain.model.Movie

@Composable
fun WatchListScreen(
    movieState: State<UiState<List<Movie>>>,
    onMovieClicked: ((id: Int) -> Unit)
) {
    MovieVerticalList(
        title = stringResource(id = com.ishom.core.R.string.watchlist),
        pageType = PageType.WatchList,
        state = movieState.value,
        onMovieClicked = onMovieClicked
    )
}