package com.ishom.app.template.widget

import com.ishom.movie.R

sealed class BottomNavigationItem(var title: String, var icon: Int, var screenRoute: String) {

    data object Movie: BottomNavigationItem(
        title = "Movies", icon = R.drawable.ic_movie, screenRoute = "movies")
    data object WatchList: BottomNavigationItem(
        title = "WatchList", icon = R.drawable.ic_movie_watch_list, screenRoute = "watch_list")
}