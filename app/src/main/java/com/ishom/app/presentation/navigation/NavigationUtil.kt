package com.ishom.app.presentation.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateMovieDetail(id: Int) {
    navigate(MovieDetailDestination.createRoute(id))
}

fun NavHostController.navigateSearch() {
    navigate(SearchDestination.route)
}