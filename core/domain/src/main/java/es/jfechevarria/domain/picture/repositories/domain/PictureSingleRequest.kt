package es.jfechevarria.domain.picture.repositories.domain

import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.picture.valueObjects.IdPictureVO
import es.jfechevarria.domain.user.valueObjects.IdUserVO

class PictureSingleRequest(
    _id: String
): RepositoryRequest() {
    val id: IdPictureVO = IdPictureVO(_id)
}