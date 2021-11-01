package es.jfechevarria.domain.result

import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.common.exceptions.GenericAppException

sealed class AppResult<out T> {
    object Loading : AppResult<Nothing>()
    data class Success<out R>(val response: R): AppResult<R>()
    data class Failure(val cause: AppException = GenericAppException()): AppResult<Nothing>()
}