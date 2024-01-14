package com.ishom.app.template.widget.movie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ishom.app.constant.Route
import com.ishom.app.template.style.Fonts
import com.ishom.app.template.widget.ErrorMessage
import com.ishom.app.template.widget.Loading
import com.ishom.movie.domain.model.Movie
import com.ishom.movie.data.source.repository.Resource

enum class PageType {
    SearchDefault,
    Searching,
    WatchList,
}

@Composable
fun MovieVerticalList(
    navigator: NavHostController,
    title: String,
    pageType: PageType? = null,
    state: State<Resource<List<Movie>>?>,
) {
    Column {
        Text(
            modifier = Modifier.padding(top = 12.dp, start = 16.dp, bottom = 12.dp),
            text = title,
            style = Fonts.Typography.h3
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(),
            columns = GridCells.Adaptive(minSize = 320.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when(state.value) {
                is Resource.Error -> item { ErrorMessage(errorMessage = state.value?.message ?:"") }
                is Resource.Success -> {
                    val movies = state.value!!.data!!
                    if (movies.isEmpty()) {
                        item {
                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = when (pageType) {
                                    PageType.Searching -> "Movie not found"
                                    PageType.WatchList ->
                                        "Please add favorite movie to showing in watchlist"
                                    PageType.SearchDefault ->
                                        "Please type search for searching movies"
                                    else -> "-"
                                },
                                style = Fonts.Typography.body1.copy(color = Color.Gray)
                            )
                        }
                    }
                    else {
                        items(count = movies.size) { index ->
                            MovieVerticalItem(
                                movie = movies[index],
                                onClickItem = { id ->
                                    navigator.navigate("${Route.PAGE_MOVIE_DETAIL}/$id")
                                }
                            )
                        }
                    }
                }
                else -> item { Loading() }
            }
            item { Spacer(modifier = Modifier.height(58.dp)) }
        }
    }
}

@Composable
fun MovieVerticalItem(
    movie: Movie,
    onClickItem: (id: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(180.dp)
            .clickable {
                onClickItem.invoke(movie.id)
            },
        verticalAlignment = Alignment.Top,
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .width(120.dp),
            model = movie.path,
            contentScale = ContentScale.Crop,
            contentDescription = movie.originalTitle
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movie.originalTitle ?: "",
                style = Fonts.Typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.overview ?: "",
                style = Fonts.Typography.body1.copy(color = Color.Gray),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}