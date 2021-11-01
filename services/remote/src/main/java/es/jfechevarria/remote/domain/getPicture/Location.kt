package es.jfechevarria.remote.domain.getPicture

import es.jfechevarria.domain.pictureLocation.LocationPicture
import es.jfechevarria.domain.pictureLocation.valueObjects.CityLocationVO
import es.jfechevarria.domain.pictureLocation.valueObjects.CountryLocationVO
import es.jfechevarria.domain.pictureLocation.valueObjects.PositionLocationVO

internal data class Location(
    val city: String?,
    val country: String?,
    val name: String?,
    val position: Position,
    val title: String?
) {
    fun toDomain(): LocationPicture {
        return LocationPicture(
            if (city == null) null else CityLocationVO(city),
            if (country == null) null else CountryLocationVO(country),
            position.toDomain()
        )
    }
}