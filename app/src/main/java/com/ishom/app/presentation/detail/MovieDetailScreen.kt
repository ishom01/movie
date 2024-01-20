package com.ishom.app.presentation.detail
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ishom.app.presentation.ui.template.widget.ErrorMessage
import com.ishom.app.presentation.ui.template.widget.Loading
import com.ishom.app.presentation.ui.template.widget.MainBar
import com.ishom.app.presentation.ui.template.widget.MainBarType
import com.ishom.app.presentation.ui.template.widget.movie.MovieDetailContent
import com.ishom.app.presentation.util.getFavoriteModule
import com.ishom.core.R
import com.ishom.movie.data.source.Resource
import com.ishom.movie.domain.model.MovieDetail

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    state: State<Resource<MovieDetail>?>,
    isHasFavoriteModule: Boolean  = false,
    onBackPressed: (() -> Unit)? = null,
    onSelectedFavorite: (() -> Unit)? = null,
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isTablet = screenWidth >= 600

    Scaffold(
        topBar = {
            MainBar(
                title = if (isTablet) stringResource(id = R.string.movie_detail) else "",
                type = MainBarType.DETAIL,
                isFavorite = state.value?.data?.isWatchList,
                isHasFavoriteModule = isHasFavoriteModule,
                buttonModifier = Modifier
                    .size(36.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(8.dp),
                onSelectedFavorite = {
                    onSelectedFavorite?.invoke()
                },
                onBackPressed = onBackPressed
            )
        }
    ) {
        when (state.value) {
            is Resource.Error -> ErrorMessage(errorMessage = state.value?.message ?: "")
            is Resource.Success -> {
                val detail = state.value?.data ?: MovieDetail()
                if (isTablet)
                    MovieDetailTablet(movie = detail)
                else
                    MovieDetailPhone( movie = detail, width = screenWidth)
            }
            else -> Loading()
        }
    }
}

@Composable
fun MovieDetailPhone(
    movie: MovieDetail,
    width: Int,
) {
    val imageHeight = 1.2 * width
    Box(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = ((-58).dp))
                .height(imageHeight.dp),
            model = movie.path,
            contentScale = ContentScale.Crop,
            contentDescription = movie.title
        )
        LazyColumn {
            item {
                Box(modifier = Modifier.height((imageHeight - 90).dp))
            }
            item {
                MovieDetailContent(
                    movie = movie,
                    mainModifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .fillMaxWidth()
                        .padding(24.dp)
                )
            }
        }
    }
}

@Composable
fun MovieDetailTablet(
    movie: MovieDetail
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier.weight(8f),
        ){
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(270.dp)
                    .width(180.dp),
                model = movie.path,
                contentScale = ContentScale.Crop,
                contentDescription = movie.originalTitle
            )
            Spacer(modifier = Modifier.width(16.dp))
            MovieDetailContent(
                movie = movie,
                mainModifier = Modifier
                    .background(
                        color = Color.LightGray.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
