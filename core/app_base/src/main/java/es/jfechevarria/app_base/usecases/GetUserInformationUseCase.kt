package es.jfechevarria.app_base.usecases

import es.jfechevarria.domain.result.AppResult
import es.jfechevarria.domain.user.User
import es.jfechevarria.domain.user.repositories.UserRepository
import es.jfechevarria.domain.user.repositories.UserRequest


class GetUserInformationUseCase (
    private val userRepository: UserRepository
): AbstractUseCase<UserRequest, User>() {
    override suspend fun execute(params: UserRequest): AppResult<User> {
        return userRepository.find(params)
    }
}