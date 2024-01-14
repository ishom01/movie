package com.ishom.movie.data.source.repository

import com.ishom.movie.data.source.remote.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<T, R> {
    private var _result: Flow<Resource<R>> = flow {
        emit(Resource.Loading())
        when (val result = onCall().first()) {
            is Response.Success ->
                emit(Resource.Success(onParse(result.data)))
            is Response.Empty ->
                emit(Resource.Success(onDefault()))
            is Response.Error ->
                emit(Resource.Error(result.errorMessage))
        }
    }

    abstract suspend fun onParse(data: T): R
    abstract suspend fun onDefault(): R
    abstract suspend fun onCall(): Flow<Response<T>>

    val asFlow get() = _result
}