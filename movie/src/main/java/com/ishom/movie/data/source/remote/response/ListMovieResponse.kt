package com.ishom.movie.data.source.remote.response
import com.google.gson.annotations.SerializedName


data class ListMovieResponse(
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("results")
    var results: List<MovieResponse>? = listOf(),
    @SerializedName("total_pages")
    var totalPages: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0
)
