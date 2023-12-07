package com.example.wheelshare_client.util

import io.ktor.client.plugins.*
import kotlinx.coroutines.flow.*

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: CustomMessage) : Result<Nothing>
    object Loading : Result<Nothing>

    object Idle : Result<Nothing>
}

fun <T> Flow<T>.asResult(shouldEmit: Boolean = true): Flow<Result<T>> {
    return this.map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch {
            if (it is Exception) {
                if (shouldEmit) {
                    emit(Result.Error(it.getRealException()))
                }
            } else {
                emit(Result.Error(CustomMessage.ExceptionMessage("System not responding.")))
            }
        }
}

fun Exception.getRealException(): CustomMessage {
    return when (this) {
        is HttpRequestTimeoutException,
        is RedirectResponseException,
        is ClientRequestException -> {
            CustomMessage.NetworkError
        }
        is ServerResponseException -> {
            val errorMessage =
                when (val statusCode = response.status.value) {
                    in 503..503 -> "No Internet."
                    in 400..499 -> "Client Error: $statusCode"
                    in 500..599 -> "Server Error: $statusCode"
                    else -> "Response Error: $statusCode"
                }
            CustomMessage.ExceptionMessage(errorMessage)
        }
        else -> {
            CustomMessage.ExceptionMessage("Something went wrong")
        }
    }
}

sealed class CustomMessage(val message: String = "") {

    object NetworkError : CustomMessage("Something wrong with network, please try again.")
    object RandomError : CustomMessage("Something went wrong, please try again.")
    object ResponseError :
        CustomMessage("We are fixing your problem, Thank you for your patience.")

    object NoInternet : CustomMessage("No Internet")
    object NotFound : CustomMessage("Not Found")
    data class ExceptionMessage(val error: String) : CustomMessage(message = error)
}
