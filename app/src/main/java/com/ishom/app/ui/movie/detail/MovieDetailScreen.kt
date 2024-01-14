package com.ishom.app.ui.movie.detail
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ishom.app.template.widget.ErrorMessage
import com.ishom.app.template.widget.Loading
import com.ishom.app.template.widget.MainBar
import com.ishom.app.template.widget.MainBarType
import com.ishom.app.template.widget.movie.MovieDetailContent
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.repository.Resource
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    controller: NavHostController,
    id: Int,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isTablet = screenWidth >= 600

    val coroutineScope = rememberCoroutineScope()

    viewModel.getDetail(id)
    val movieState = viewModel.movieDetail.observeAsState()

    Scaffold(
        topBar = {
            MainBar(
                controller = controller,
                title = if (isTablet) "Movie Detail" else "",
                type = MainBarType.DETAIL,
                isWatchList = movieState.value?.data?.isWatchList,
                onSelectedFavorite = {
                    coroutineScope.launch {
                        movieState.value?.data?.let { movie ->
                            viewModel.setWatchlist(movie)
                        }
                    }
                }
            )
        }
    ) {
        when (movieState.value) {
            is Resource.Error -> ErrorMessage(errorMessage = movieState.value?.message ?: "")
            is Resource.Success -> {
                val detail = movieState.value?.data ?: MovieDetail()
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
