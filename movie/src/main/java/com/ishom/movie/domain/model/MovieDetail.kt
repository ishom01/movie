package com.ishom.movie.domain.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

data class MovieDetail(
    var adult: Boolean = false,
    var isWatchList: Boolean = false,
    var backdropPath: String? = "",
    var budget: Int? = 0,
    var genres: List<Genre> = listOf(),
    var homepage: String? = "",
    var id: Int = 0,
    var imdbId: String? = "",
    var originalLanguage: String? = "",
    var originalTitle: String? = "",
    var overview: String? = "",
    var popularity: Double? = 0.0,
    var posterPath: String? = "",
    var releaseDate: String? = "",
    var revenue: Int? = 0,
    var runtime: Int = 0,
    var status: String? = "",
    var tagline: String? = "",
    var title: String? = "",
    var video: Boolean? = false,
    var voteAverage: Double? = 0.0,
    var voteCount: Int? = 0
) {
    val path: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"

    val duration: String
        get() {
            var time = "${runtime / 60} hours"
            if (runtime % 60 > 0) time += " ${runtime % 60} minutes"
            return time
        }

    val year: String
        get() {
            return try {
                val calendar = Calendar.getInstance()
                calendar.time = SimpleDateFormat("yyyy-MM-dd").parse(releaseDate!!)!!
                calendar.get(Calendar.YEAR).toString()
            }
            catch (e: Exception) {
                "-"
            }
        }
}