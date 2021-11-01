package es.jfechevarria.domain.state

import es.jfechevarria.domain.common.exceptions.AppException

sealed class AppState<out T>(val loading: Boolean) {
    object Idle: AppState<Nothing>(true)
    data class Data<out R>(val response: R): AppState<R>(false)
    data class Exception(val cause: AppException): AppState<Nothing>(false)
}