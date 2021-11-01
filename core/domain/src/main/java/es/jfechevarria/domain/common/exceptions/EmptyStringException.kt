package es.jfechevarria.domain.common.exceptions

class EmptyStringException(className: String):
    AppException("Value object of class $className is empty.") {
}