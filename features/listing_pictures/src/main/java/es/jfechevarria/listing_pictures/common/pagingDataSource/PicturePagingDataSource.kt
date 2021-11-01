package es.jfechevarria.listing_pictures.common.pagingDataSource

import es.jfechevarria.app_base.pagingSources.AbstractPagingSource
import es.jfechevarria.app_base.usecases.GetPicturesUseCase
import es.jfechevarria.domain.common.exceptions.GenericAppException
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.repositories.domain.PictureOrderBy
import es.jfechevarria.domain.picture.repositories.domain.PicturesRequest
import es.jfechevarria.domain.result.AppResult
import kotlinx.coroutines.flow.*

class PicturePagingDataSource(
    private val getPicturesUseCase: GetPicturesUseCase,
    private val pictureOrderBy: PictureOrderBy
): AbstractPagingSource<Picture>() {
    override suspend fun call(page: Int): Flow<List<Picture>> = flow {
        getPicturesUseCase(PicturesRequest(page, orderBy = pictureOrderBy))
            .catch { throw it }
            .onEach {
                when (val response = it) {
                    is AppResult.Loading -> {}
                    is AppResult.Success -> {
                        emit(response.response)
                    }
                    is AppResult.Failure -> {
                        throw response.cause
                    }
                }
            }.collect()
    }
}