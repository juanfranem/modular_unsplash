package es.jfechevarria.remote.domain.getPicture

import es.jfechevarria.domain.pictureLocation.valueObjects.PositionLocationVO

internal data class Position(
    val latitude: Double?,
    val longitude: Double?
) {
    fun toDomain(): PositionLocationVO? {
        if (latitude != null && longitude != null) {
            return PositionLocationVO(latitude to longitude)
        }
        return null
    }
}