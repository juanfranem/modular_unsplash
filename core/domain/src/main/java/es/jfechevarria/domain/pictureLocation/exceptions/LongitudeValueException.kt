package es.jfechevarria.domain.pictureLocation.exceptions

import es.jfechevarria.domain.common.exceptions.AppException

class LongitudeValueException(value: Double): AppException("Longitude value must be between -180/180. Current value is: $value") {
}