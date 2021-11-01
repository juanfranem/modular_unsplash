package es.jfechevarria.domain.common.dataSources

import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.result.AppResult

interface RemoteDataSource<T> {
    suspend fun find(repositoryRequest: RepositoryRequest): AppResult<T>
    suspend fun get(repositoryRequest: RepositoryRequest): AppResult<List<T>>
}