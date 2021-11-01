package es.jfechevarria.domain.common.valueObjects

import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.common.exceptions.NullValueException

abstract class ValueObject<T>(private val _value: T?) {

    open val validationRules: List<Pair<Boolean, AppException>> = listOf()

    val value: T
        get() = _value!!

    init {
        if (_value == null) throw NullValueException("Value of class ${this.javaClass} can´t be null.")
        validateDataObject()
    }

    @Throws
    private fun validateDataObject() {
        validationRules.firstOrNull { it.first }?.let {
            throw it.second
        }
    }
}