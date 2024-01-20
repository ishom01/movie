package com.ishom.movie.data.source.remote.response

sealed class Response<out R> {
    class Success<out T>(val data: T): Response<T>()
    data object Empty: Response<Nothing>()
    class Error<T>(val errorMessage: String): Response<T>()
}