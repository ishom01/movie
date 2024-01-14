package com.ishom.watchlist.ui.watchlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ishom.app.template.widget.movie.MovieVerticalList
import com.ishom.app.template.widget.movie.PageType

@Composable
fun WatchListScreen(
    controller: NavHostController,
    viewModel: WatchlistViewModel = hiltViewModel()
) {
    val state = viewModel.watchList.observeAsState()
    MovieVerticalList(
        navigator = controller,
        title = "Watchlist",
        pageType = PageType.WatchList,
        state = state
    )
}