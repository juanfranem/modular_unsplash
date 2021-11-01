package es.jfechevarria.domain.user.dataSources

import es.jfechevarria.domain.common.dataSources.RemoteDataSource
import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.result.AppResult
import es.jfechevarria.domain.user.User
import es.jfechevarria.domain.user.repositories.UserRequest

interface UserRemoteDataSource: RemoteDataSource<User> {
}