package com.ishom.movie.domain.model

import com.ishom.movie.data.source.remote.local.entity.WatchlistEntity
import com.ishom.movie.data.source.remote.response.GenreResponse
import com.ishom.movie.data.source.remote.response.MovieDetailResponse
import com.ishom.movie.data.source.remote.response.MovieResponse

object DataMapper {

    fun mapRemoteToDomain(list: List<MovieResponse>): List<Movie> {
        return list.map {
            Movie(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                id = it.id,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
            )
        }
    }

    fun mapEntitiesToDomain(data: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            adult = data.adult,
            backdropPath = data.backdropPath,
            budget = data.budget,
            genres = data.genres.map { mapEntitiesToDomain(it) },
            homepage = data.homepage,
            id = data.id,
            imdbId = data.imdbId,
            originalLanguage = data.originalLanguage,
            originalTitle = data.originalTitle,
            overview = data.overview,
            popularity = data.popularity,
            posterPath = data.posterPath,
            releaseDate = data.releaseDate,
            revenue = data.revenue,
            runtime = data.runtime,
            status = data.status,
            tagline = data.tagline,
            title = data.title,
            video = data.video,
            voteAverage = data.voteAverage,
            voteCount = data.voteCount
        )
    }

    private fun mapEntitiesToDomain(data: GenreResponse): Genre {
        return Genre(
            id = data.id,
            name = data.name
        )
    }

    fun mapEntitiesToDomain(entities: List<WatchlistEntity>): List<Movie> {
        return entities.map { entity ->
            Movie().apply {
                id = entity.id
                title = entity.name
                overview = entity.overview
                posterPath = entity.path
            }
        }
    }

    fun mapDomainToEntities(movie: MovieDetail): WatchlistEntity {
        return WatchlistEntity(
            movie.id,
            movie.originalTitle ?: "",
            movie.posterPath ?: "",
            movie.overview ?: ""
        )
    }
}