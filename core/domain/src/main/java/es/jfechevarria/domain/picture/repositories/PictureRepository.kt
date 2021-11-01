package es.jfechevarria.domain.picture.repositories

import es.jfechevarria.domain.common.repositories.Repository
import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.repositories.domain.PictureSingleRequest
import es.jfechevarria.domain.picture.repositories.domain.PicturesRequest
import es.jfechevarria.domain.result.AppResult

interface PictureRepository: Repository<Picture> {
    suspend fun haveLocalSaved(): AppResult<Boolean>
    suspend fun find(request: PictureSingleRequest): AppResult<Picture>
    suspend fun get(request: PicturesRequest): AppResult<List<Picture>>
    suspend fun insert(vararg t: Picture): AppResult<Unit>
    suspend fun remove(t: Picture): AppResult<Unit>
}