package es.jfechevarria.domain.common.exceptions

class NegativeValueIntegerException(className: String): AppException("Value object of class $className is negative.") {
}