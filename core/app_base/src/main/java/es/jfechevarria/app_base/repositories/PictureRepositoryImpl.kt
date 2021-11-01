package es.jfechevarria.app_base.repositories

import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.dataSources.LocalPictureDataSource
import es.jfechevarria.domain.picture.dataSources.RemotePictureDataSource
import es.jfechevarria.domain.picture.repositories.PictureRepository
import es.jfechevarria.domain.picture.repositories.domain.PictureOrderBy
import es.jfechevarria.domain.picture.repositories.domain.PictureSingleRequest
import es.jfechevarria.domain.picture.repositories.domain.PicturesRequest
import es.jfechevarria.domain.picture.valueObjects.IsSavedPictureVO
import es.jfechevarria.domain.result.AppResult


internal class PictureRepositoryImpl (
    private val localPictureDataSource: LocalPictureDataSource,
    private val remotePictureDataSource: RemotePictureDataSource
): PictureRepository {
    override suspend fun haveLocalSaved(): AppResult<Boolean> {
        return AppResult.Success(!localPictureDataSource.isEmpty())
    }

    override suspend fun find(request: PictureSingleRequest): AppResult<Picture> {
        when (val response = remotePictureDataSource.find(request)) {
            is AppResult.Success -> {
                if (localPictureDataSource.isSaved(response.response)) {
                    return response.copy(
                        response = response.response.copy(
                            isSaved = IsSavedPictureVO(true)
                        )
                    )
                }
                return response

            }
            else -> {
                return response
            }
        }
    }

    override suspend fun get(request: PicturesRequest): AppResult<List<Picture>> {
        if (request.orderBy == PictureOrderBy.SAVED) {
            return AppResult.Success(localPictureDataSource.get(request))
        }
        return when (val response = remotePictureDataSource.get(request)) {
            is AppResult.Success -> {
                response.copy(response = response.response.map {
                    it.copy(
                        isSaved = IsSavedPictureVO(localPictureDataSource.isSaved(it))
                    )
                })
            }
            else -> {
                response
            }
        }
    }

    override suspend fun insert(vararg t: Picture): AppResult<Unit> {
        return try {
            localPictureDataSource.insert(*t)
            AppResult.Success(response = Unit)
        } catch (e: Exception) {
            AppResult.Failure()
        }
    }

    override suspend fun remove(t: Picture): AppResult<Unit> {
        return try {
            localPictureDataSource.remove(t)
            AppResult.Success(response = Unit)
        } catch (e: Exception) {
            AppResult.Failure()
        }
    }
}