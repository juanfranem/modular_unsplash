package es.jfechevarria.domain.common.exceptions

abstract class AppException(
    message: String? = null
): Exception(message) {
}