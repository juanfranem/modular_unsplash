package es.jfechevarria.domain.pictureLocation.valueObjects

import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.common.valueObjects.ValueObject
import es.jfechevarria.domain.pictureLocation.exceptions.LatitudeValueException
import es.jfechevarria.domain.pictureLocation.exceptions.LongitudeValueException
import kotlin.math.abs

class PositionLocationVO(value: Pair<Double, Double>): ValueObject<Pair<Double, Double>>(value) {

    override val validationRules: List<Pair<Boolean, AppException>>
        get() = listOf(
            (abs(value.first) > abs(90.0)) to LatitudeValueException(value.first),
            (abs(value.second) > abs(180.0)) to LongitudeValueException(value.second)
        )
}