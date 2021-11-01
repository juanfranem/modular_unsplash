package es.jfechevarria.domain.picture.valueObjects

import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.common.exceptions.NegativeValueIntegerException
import es.jfechevarria.domain.common.valueObjects.ValueObject

class LikesPictureVO(value: Int): ValueObject<Int>(value) {

    override val validationRules: List<Pair<Boolean, AppException>>
        get() = listOf(
            (value < 0) to NegativeValueIntegerException(this::class.java.toString())
        )
}