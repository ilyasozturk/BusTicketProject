package com.example.busticketprojectkotlin.base

import com.example.busticketprojectkotlin.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseNetworkCallRepository {

    suspend fun <T> safeApiRequest(
        apiRequest: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Error("hata", null)
                    }
                    else -> Resource.Error(throwable.localizedMessage, null)
                }
            }
        }

    }
}

