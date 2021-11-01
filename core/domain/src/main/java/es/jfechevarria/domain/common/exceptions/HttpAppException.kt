package es.jfechevarria.domain.common.exceptions

class HttpAppException(
    code: Int?, message: String?
): AppException("HTTP Exception with code: $code \r httpErrorMessage: $message") {
}