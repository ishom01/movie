package com.ishom.movie.data.source.remote.api

import com.ishom.movie.data.source.remote.response.ListMovieResponse
import com.ishom.movie.data.source.remote.response.MovieDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String,
    ): Call<ListMovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String,
    ): Call<ListMovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String,
    ): Call<ListMovieResponse>

    @GET("search/movie")
    fun getSearchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") searchKey: String,
        @Query("page") page: Int,
        @Query("language") language: String,
    ): Call<ListMovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Call<MovieDetailResponse>
}