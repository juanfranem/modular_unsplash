package es.jfechevarria.domain.user.repositories

import es.jfechevarria.domain.common.repositories.Repository
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.repositories.domain.PictureSingleRequest
import es.jfechevarria.domain.result.AppResult
import es.jfechevarria.domain.user.User

interface UserRepository: Repository<User> {
    suspend fun find(request: UserRequest): AppResult<User>
}