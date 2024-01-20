package com.ishom.app.presentation.ui.template.widget.movie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ishom.app.presentation.ui.template.style.Fonts
import com.ishom.app.presentation.ui.template.widget.ErrorMessage
import com.ishom.app.presentation.ui.template.widget.Loading
import com.ishom.movie.data.source.Resource
import com.ishom.movie.domain.model.Movie

@Composable
fun MovieHorizontalList(
    title: String,
    scale: Float = 1f,
    state: State<Resource<List<Movie>>?>,
    onMovieClicked: ((id: Int) -> Unit)
) {
    val margin = 8 * scale

    Column(
        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = title,
            style = Fonts.Typography.h3
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height((240 * scale).dp)
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(margin.dp)
        ) {
            when(state.value) {
                is Resource.Error -> item { ErrorMessage(errorMessage = state.value?.message ?:"") }
                is Resource.Success -> {
                    val movies = state.value!!.data!!
                    item {
                        Box(modifier = Modifier.width((16 - margin).dp))
                    }
                    items(count = movies.size) { index ->
                        MovieHorizontalItem(
                            movie = movies[index],
                            scale = scale,
                            onClickItem = { id ->
                                onMovieClicked.invoke(id)
                            }
                        )
                    }
                    item {
                        Box(modifier = Modifier.width((16 - margin).dp))
                    }
                }
                else -> item { Loading() }
            }
        }
    }
}

@Composable
fun MovieHorizontalItem(
    movie: Movie,
    scale: Float = 1f,
    onClickItem: (id: Int) -> Unit
) {
    AsyncImage(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .width((160 * scale).dp)
            .fillMaxWidth()
            .clickable {
                onClickItem.invoke(movie.id)
            },
        model = movie.path,
        contentScale = ContentScale.Crop,
        contentDescription = movie.originalTitle
    )
}