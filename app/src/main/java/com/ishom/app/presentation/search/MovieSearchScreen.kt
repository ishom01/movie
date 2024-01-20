package com.ishom.app.presentation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ishom.app.presentation.ui.template.widget.MainBar
import com.ishom.app.presentation.ui.template.widget.MainBarType
import com.ishom.app.presentation.ui.template.widget.movie.MovieVerticalList
import com.ishom.app.presentation.ui.template.widget.movie.PageType
import com.ishom.core.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieSearchScreen(
    state: State<MovieSearchUiState>,
    onSearchChanged: ((key: String) -> Unit)? = null,
    onBackPressed: (() -> Unit)? = null,
    onMovieClicked: ((id: Int) -> Unit)
) {
    Scaffold(
        topBar = {
            MainBar(
                title = stringResource(id = R.string.search),
                type = MainBarType.SEARCH,
                searchKey = state.value.searchKey,
                buttonModifier = Modifier.size(18.dp),
                onSearchChanged = onSearchChanged,
                onBackPressed = onBackPressed
            )
        }
    ) {
        MovieVerticalList(
            title = stringResource(id = R.string.search_movie),
            pageType = if (state.value.searchKey.isNotEmpty()) PageType.Searching else PageType.SearchDefault,
            state = state.value.moviesState,
            onMovieClicked = onMovieClicked
        )
    }
}