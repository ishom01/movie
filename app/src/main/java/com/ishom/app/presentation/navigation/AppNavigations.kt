package com.ishom.app.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ishom.app.presentation.UiState
import com.ishom.app.presentation.detail.MovieDetailScreen
import com.ishom.app.presentation.detail.MovieDetailViewModel
import com.ishom.app.presentation.list.MovieListScreen
import com.ishom.app.presentation.list.MovieListViewModel
import com.ishom.app.presentation.search.MovieSearchScreen
import com.ishom.app.presentation.search.MovieSearchViewModel
import com.ishom.app.presentation.util.getFavoriteModule
import com.ishom.app.presentation.watchlist.FavoriteViewModel
import com.ishom.movie.domain.model.Movie
import java.lang.reflect.Method

@Composable
fun MainNavigation(
    controller: NavHostController = rememberNavController(),
) {
    val favoriteMethod = getFavoriteModule()
    NavHost(
        navController = controller,
        startDestination = HomeDestination.route
    ) {
        composable(
            route = HomeDestination.route
        ) {
            val viewModel: MovieListViewModel = hiltViewModel()
            MovieListScreen(
                popularMovieState = viewModel.popularMovies.observeAsState(),
                nowPlayingMovieState = viewModel.nowMovies.observeAsState(),
                upcomingMovieState = viewModel.upcomingMovies.observeAsState(),
                onMovieClicked = {
                    controller.navigateMovieDetail(it)
                }
            )
        }
        composable(
            route = WatchlistDestination.route
        ) {
            val viewModel: FavoriteViewModel = hiltViewModel()
            CheckFavoriteFeatureExist(
                method = favoriteMethod,
                movieState = viewModel.favoriteState.collectAsStateWithLifecycle(),
                onMovieClicked = {
                    controller.navigateMovieDetail(it)
                }
            )
        }
        composable(
            route = SearchDestination.route
        ) {
            val viewModel: MovieSearchViewModel = hiltViewModel()
            MovieSearchScreen(
                state = viewModel.state.collectAsStateWithLifecycle(),
                onSearchChanged = { 
                    viewModel.onSearchKey(it)
                },
                onMovieClicked = {
                    controller.navigateMovieDetail(it)
                },
                onBackPressed = {
                    controller.navigateUp()
                }
            )
        }
        composable(
            route = MovieDetailDestination.route,
            arguments = listOf(
                navArgument(name = PARAM_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            viewModel.getDetail(it.arguments?.getInt(PARAM_ID) ?: -1)
            MovieDetailScreen(
                state = viewModel.movieDetail.collectAsStateWithLifecycle(),
                isHasFavoriteModule = favoriteMethod != null,
                onBackPressed = {
                    controller.navigateUp()
                },
                onSelectedFavorite = {
                    viewModel.setWatchlist()
                }
            )
        }
    }
}

@Composable
fun CheckFavoriteFeatureExist(
    method: Method? = null,
    movieState: State<UiState<List<Movie>>>,
    onMovieClicked: ((id: Int) -> Unit),
) {
    if (method != null) {
        method.isAccessible = true
        method.invoke(null, movieState, onMovieClicked, currentComposer, 0)
    }
    else {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Favorite module not found"
        )
    }
}