package es.jfechevarria.domain.common.exceptions

class UrlFormatException(className: String): AppException("Value object of class $className is not a valid URL format.") {
}