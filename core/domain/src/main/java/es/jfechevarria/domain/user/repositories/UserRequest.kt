package es.jfechevarria.domain.user.repositories

import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.user.valueObjects.IdUserVO

data class UserRequest(
    private val _id: String
): RepositoryRequest() {
    val id: IdUserVO = IdUserVO(_id)
}