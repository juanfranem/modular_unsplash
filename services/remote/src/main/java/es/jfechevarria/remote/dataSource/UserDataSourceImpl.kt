package es.jfechevarria.remote.dataSource

import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.common.exceptions.GenericAppException
import es.jfechevarria.domain.common.exceptions.HttpAppException
import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.result.AppResult
import es.jfechevarria.domain.user.User
import es.jfechevarria.domain.user.dataSources.UserRemoteDataSource
import es.jfechevarria.domain.user.repositories.UserRequest
import es.jfechevarria.remote.ApiService
import retrofit2.HttpException
import java.lang.Exception


internal class UserDataSourceImpl (
    private val apiService: ApiService
): UserRemoteDataSource {

    override suspend fun find(repositoryRequest: RepositoryRequest): AppResult<User> {
        return try {
            val response = apiService.getUserInformation((repositoryRequest as UserRequest).id.value)
                .toDomain()
            AppResult.Success(response)
        } catch (exception: AppException) {
            AppResult.Failure(exception)
        } catch (exception: HttpException) {
            AppResult.Failure(HttpAppException(exception.code(), exception.message()))
        } catch (exception: Exception) {
            AppResult.Failure(GenericAppException(exception.message))
        }
    }

    override suspend fun get(repositoryRequest: RepositoryRequest): AppResult<List<User>> {
        return AppResult.Failure(GenericAppException())
    }
}