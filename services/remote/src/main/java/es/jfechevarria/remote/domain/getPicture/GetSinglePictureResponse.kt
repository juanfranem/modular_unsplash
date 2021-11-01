package es.jfechevarria.remote.domain.getPicture

import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.valueObjects.*
import es.jfechevarria.remote.domain.common.SimpleUser
import es.jfechevarria.remote.domain.common.Urls

internal data class GetSinglePictureResponse(
    val description: String?,
    val exif: Exif,
    val id: String,
    val likes: Int,
    val location: Location,
    val urls: Urls,
    val user: SimpleUser,
) {
    fun toDomain(): Picture {
        return Picture(
            IdPictureVO(id),
            if (description != null) DescriptionPictureVO(description) else null,
            ImageURIPictureVO(urls.full),
            LikesPictureVO(likes),
            CreatorUserNamePictureVO(user.username),
            exif.toDomain(),
            location.toDomain()
        )
    }
}

