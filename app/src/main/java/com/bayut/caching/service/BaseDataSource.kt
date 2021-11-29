package com.bayut.caching.service

import retrofit2.Response


/**
 * Abstract Base Data source class with Error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {

                val body = response.body()
                if (body != null)
                    return Result.Success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        return Result.Error(message)
    }

}

