package com.ishom.app.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ishom.movie.R

const val PARAM_ID = "id"

private const val PAGE_HOME = "home"
private const val PAGE_WATCHLIST = "watchlist"
private const val PAGE_MOVIE_DETAIL = "movie_detail"
private const val PAGE_MOVIE_SEARCH = "movie_search"

sealed class Destination(val route: String) {
    companion object
}

sealed class BottomNavDestination(
    route: String,
    @DrawableRes val selectedIcon: Int,
    @StringRes val label: Int
): Destination(route) {
    companion object
}

val BottomNavDestination.Companion.allBottomNavDestinations get() = buildList {
    add(HomeDestination)
    add(WatchlistDestination)
}

val Destination.Companion.allDestinations get() = buildList {
    add(HomeDestination)
    add(WatchlistDestination)
    add(MovieDetailDestination)
    add(SearchDestination)
}

object HomeDestination: BottomNavDestination(
    route = PAGE_HOME,
    selectedIcon = R.drawable.ic_movie,
    label = com.ishom.core.R.string.home,
)

data object WatchlistDestination: BottomNavDestination(
    route = PAGE_WATCHLIST,
    selectedIcon = R.drawable.ic_movie_watch_list,
    label = com.ishom.core.R.string.watchlist,
)

data object SearchDestination: Destination(route = PAGE_MOVIE_SEARCH)

data object MovieDetailDestination: Destination(route = "$PAGE_MOVIE_DETAIL/{$PARAM_ID}") {
    fun createRoute(movieId: Int) = "$PAGE_MOVIE_DETAIL/$movieId"
}