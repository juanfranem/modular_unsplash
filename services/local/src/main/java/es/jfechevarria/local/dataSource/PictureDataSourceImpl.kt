package es.jfechevarria.local.dataSource

import es.jfechevarria.domain.common.repositories.RepositoryRequest
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.dataSources.LocalPictureDataSource
import es.jfechevarria.local.dao.PictureDao
import es.jfechevarria.local.domain.PictureDB.Companion.toDB


internal class PictureDataSourceImpl (private val pictureDao: PictureDao)
    : LocalPictureDataSource {

    override suspend fun isSaved(picture: Picture): Boolean {
        return pictureDao.exists(picture.id.value) != 0
    }

    override suspend fun isEmpty(): Boolean {
        return pictureDao.count() == 0
    }

    override suspend fun get(repositoryRequest: RepositoryRequest): List<Picture> {
        return pictureDao.get((repositoryRequest.page - 1) * repositoryRequest.total, repositoryRequest.total)
            .map { it.toDomain() }

    }

    override suspend fun insert(vararg t: Picture) {
        pictureDao.insert(*t.map { it.toDB() }.toTypedArray())
    }

    override suspend fun remove(t: Picture) {
        pictureDao.delete(t.id.value)
    }
}