package com.ishom.app.ui.movie.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ishom.app.template.widget.MainBar
import com.ishom.app.template.widget.MainBarType
import com.ishom.app.template.widget.movie.MovieVerticalList
import com.ishom.app.template.widget.movie.PageType

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieSearchScreen(
    controller: NavHostController,
    viewModel: MovieSearchViewModel = hiltViewModel()
) {
    val searchKey by viewModel.searchKey.collectAsState()
    Scaffold(
        topBar = {
            MainBar(
                controller = controller,
                title = "Search",
                type = MainBarType.SEARCH,
                searchKey = searchKey,
                onSearchChanged = {
                    viewModel.onSearchKey(it)
                }
            )
        }
    ) {
        MovieVerticalList(
            navigator = controller,
            title = "Search Movies",
            pageType = if (searchKey.isNotEmpty()) PageType.Searching else PageType.SearchDefault,
            state = viewModel.movies.observeAsState()
        )
    }
}