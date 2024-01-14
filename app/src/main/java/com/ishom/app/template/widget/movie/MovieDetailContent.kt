package com.ishom.app.template.widget.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ishom.app.template.style.Fonts
import com.ishom.movie.domain.model.Genre
import com.ishom.movie.domain.model.MovieDetail


@Composable
fun MovieDetailContent(
    movie: MovieDetail,
    mainModifier: Modifier,
) {
    Column(modifier = mainModifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = movie.originalTitle ?: "",
                style = Fonts.Typography.h6
            )
            Text(
                text = "%.1f".format(movie.voteAverage ?: 0),
                style = Fonts.Typography.h1.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Search",
                tint = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.year,
            style = Fonts.Typography.h3.copy(color = Color.DarkGray)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.duration,
            style = Fonts.Typography.body1.copy(color = Color.Gray)
        )

        // region genres
        Header(title = "Genres")
        MovieGenreContent(genres = movie.genres)
        // endregion

        // region descriptions
        Header(title = "Description")
        Text(
            text = movie.overview ?: "",
            style = Fonts.Typography.body1.copy(color = Color.Gray)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieGenreContent(
    genres: List<Genre>
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        genres.forEach {
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Red,
                        shape = RoundedCornerShape(16.dp)
                    ),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    text = it.name ?: "",
                    color = Color.White,
                    style = Fonts.Typography.h4
                )
            }
        }
    }
}

@Composable
fun Header(
    title: String
) {
    Row(
        modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .background(color = Color.Red)
                .height(12.dp)
                .width(4.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = Fonts.Typography.h3
        )
    }
}

