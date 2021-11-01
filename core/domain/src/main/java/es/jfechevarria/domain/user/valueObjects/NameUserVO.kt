package es.jfechevarria.domain.user.valueObjects

import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.common.exceptions.EmptyStringException
import es.jfechevarria.domain.common.valueObjects.ValueObject

class NameUserVO(value: String): ValueObject<String>(value) {

    override val validationRules: List<Pair<Boolean, AppException>>
        get() = listOf(
            value.isEmpty() to EmptyStringException(this::class.java.toString())
        )

}