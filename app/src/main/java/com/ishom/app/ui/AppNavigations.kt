package com.ishom.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ishom.app.constant.Route
import com.ishom.app.ui.home.HomeScreen
import com.ishom.app.ui.movie.detail.MovieDetailScreen
import com.ishom.app.ui.movie.detail.MovieDetailViewModel
import com.ishom.app.ui.movie.search.MovieSearchScreen

@Composable
fun MainNavigation(
    controller: NavHostController,
) {
    NavHost(navController = controller, startDestination = Route.PAGE_HOME) {
        composable(Route.PAGE_HOME) {
            HomeScreen(controller)
        }
        composable("${Route.PAGE_MOVIE_DETAIL}/{${Route.PARAM_MOVIE_ID}}",
            arguments = listOf(navArgument(Route.PARAM_MOVIE_ID) {
                type = NavType.IntType
            })
        ) {
            MovieDetailScreen(controller, id = it.arguments?.getInt(Route.PARAM_MOVIE_ID) ?: 0)
        }
        composable(Route.PAGE_MOVIE_SEARCH) {
            MovieSearchScreen(controller)
        }
    }
}