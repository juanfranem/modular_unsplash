package es.jfechevarria.app_base.repositories

import es.jfechevarria.domain.result.AppResult
import es.jfechevarria.domain.user.User
import es.jfechevarria.domain.user.dataSources.UserRemoteDataSource
import es.jfechevarria.domain.user.repositories.UserRepository
import es.jfechevarria.domain.user.repositories.UserRequest


internal class UserRepositoryImpl (
    private val userRemoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun find(request: UserRequest): AppResult<User> {
        return userRemoteDataSource.find(request)
    }
}