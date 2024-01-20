package com.ishom.movie.domain

import com.ishom.movie.domain.model.Movie
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.Resource
import com.ishom.movie.domain.repository.MovieRepository
import com.ishom.movie.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val watchlistRepository: WatchlistRepository,
): MovieUseCase {
    override fun getNowPlaying(page: Int?): Flow<Resource<List<Movie>>> =
        movieRepository.getNowPlaying()

    override fun getPopular(page: Int?): Flow<Resource<List<Movie>>> =
        movieRepository.getPopular(page)

    override fun getUpcoming(page: Int?): Flow<Resource<List<Movie>>> =
        movieRepository.getUpcomingMovie(page)

    override fun getSearchMovie(query: String, page: Int?): Flow<Resource<List<Movie>>> =
        movieRepository.getSearchMovie(query, page)

    override fun getDetail(id: Int): Flow<Resource<MovieDetail>> = movieRepository.getDetail(id)

    override suspend fun checkFavorite(movieDetail: MovieDetail) {
        if (movieDetail.isWatchList)
            watchlistRepository.insert(movieDetail)
        else
            watchlistRepository.remove(movieDetail.id)
    }
}