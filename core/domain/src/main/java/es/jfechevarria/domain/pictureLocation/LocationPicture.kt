package es.jfechevarria.domain.pictureLocation

import es.jfechevarria.domain.pictureLocation.valueObjects.CityLocationVO
import es.jfechevarria.domain.pictureLocation.valueObjects.CountryLocationVO
import es.jfechevarria.domain.pictureLocation.valueObjects.PositionLocationVO

data class LocationPicture(
    val city: CityLocationVO? = null,
    val country: CountryLocationVO? = null,
    val position: PositionLocationVO? = null
)