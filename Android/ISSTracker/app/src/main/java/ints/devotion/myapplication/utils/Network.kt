package ints.devotion.myapplication.utils

import android.annotation.SuppressLint
import android.net.http.HttpException
import ints.devotion.myapplication.model.error.ErrorResponse
import ints.devotion.myapplication.model.error.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

@SuppressLint("NewApi")
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    ResultWrapper.GenericError(0, ErrorResponse(
                        0, throwable.message ?: "", ""
                    )
                    )
                }
                else -> ResultWrapper.GenericError(null, null)
            }
        }
    }
}
