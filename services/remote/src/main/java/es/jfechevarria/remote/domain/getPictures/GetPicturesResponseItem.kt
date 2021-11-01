package es.jfechevarria.remote.domain.getPictures

import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.valueObjects.*
import es.jfechevarria.remote.domain.common.SimpleUser
import es.jfechevarria.remote.domain.common.Urls

internal data class GetPicturesResponseItem(
    val description: String?,
    val id: String,
    val likes: Int,
    val urls: Urls,
    val user: SimpleUser
) {
    fun toDomain(): Picture {
        return Picture(
            IdPictureVO(id),
            if (description != null) DescriptionPictureVO(description) else null,
            ImageURIPictureVO(urls.thumb),
            LikesPictureVO(likes),
            CreatorUserNamePictureVO(user.username)
        )
    }
}