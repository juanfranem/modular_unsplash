package es.jfechevarria.domain.common.dataSources

import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.result.AppResult

interface LocalDataSource<T> {
    suspend fun get(repositoryRequest: RepositoryRequest): List<T>
    suspend fun insert(vararg t: T)
    suspend fun remove(t: T)
}