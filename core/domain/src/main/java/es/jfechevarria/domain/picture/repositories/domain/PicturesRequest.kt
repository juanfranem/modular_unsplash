package es.jfechevarria.domain.picture.repositories.domain

import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.picture.valueObjects.IdPictureVO

class PicturesRequest(
    page: Int = 1,
    total: Int = 10,
    val orderBy: PictureOrderBy
): RepositoryRequest(page, total) {
}