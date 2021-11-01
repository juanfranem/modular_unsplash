package es.jfechevarria.remote.dataSource

import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.common.exceptions.GenericAppException
import es.jfechevarria.domain.common.exceptions.HttpAppException
import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.dataSources.RemotePictureDataSource
import es.jfechevarria.domain.picture.repositories.domain.PictureSingleRequest
import es.jfechevarria.domain.picture.repositories.domain.PicturesRequest
import es.jfechevarria.domain.result.AppResult
import es.jfechevarria.remote.ApiService
import retrofit2.HttpException
import java.lang.Exception

internal class PictureDataSourceImpl (
    private val apiService: ApiService
): RemotePictureDataSource {

    override suspend fun find(repositoryRequest: RepositoryRequest): AppResult<Picture> {
        return try {
            val response = apiService.getPicture((repositoryRequest as PictureSingleRequest).id.value)
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

    override suspend fun get(repositoryRequest: RepositoryRequest): AppResult<List<Picture>> {
        return try {
            val response = apiService.getPictures(
                (repositoryRequest as PicturesRequest).page,
                repositoryRequest.total,
                (repositoryRequest).orderBy.toString(),
            ).map { it.toDomain() }
            AppResult.Success(response)
        } catch (exception: AppException) {
            AppResult.Failure(exception)
        } catch (exception: HttpException) {
            AppResult.Failure(HttpAppException(exception.code(), exception.message()))
        } catch (exception: Exception) {
            AppResult.Failure(GenericAppException(exception.message))
        }
    }
}