package com.ishom.favorite.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import com.ishom.app.presentation.UiState
import com.ishom.app.presentation.ui.template.widget.movie.MovieVerticalList
import com.ishom.app.presentation.ui.template.widget.movie.PageType
import com.ishom.core.R
import com.ishom.movie.domain.model.Movie

@Composable
fun FavoriteUI(
    onMovieClicked: ((id: Int) -> Unit),
    movieState: State<UiState<List<Movie>>>,
) {
    MovieVerticalList(
        title = stringResource(id = R.string.watchlist),
        pageType = PageType.WatchList,
        state = movieState.value,
        onMovieClicked = onMovieClicked
    )
}