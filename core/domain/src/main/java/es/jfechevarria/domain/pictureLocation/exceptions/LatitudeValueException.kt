package es.jfechevarria.domain.pictureLocation.exceptions

import es.jfechevarria.domain.common.exceptions.AppException

class LatitudeValueException(value: Double): AppException("Latitude value must be between -90/90. Current value is: $value") {
}