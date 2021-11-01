package es.jfechevarria.domain.picture.dataSources

import es.jfechevarria.domain.common.dataSources.LocalDataSource
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.valueObjects.IdPictureVO

interface LocalPictureDataSource: LocalDataSource<Picture> {
    suspend fun isSaved(picture: Picture): Boolean
    suspend fun isEmpty(): Boolean
}