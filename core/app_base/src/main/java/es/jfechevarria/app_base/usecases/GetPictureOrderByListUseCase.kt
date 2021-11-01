package es.jfechevarria.app_base.usecases

import es.jfechevarria.domain.picture.repositories.PictureRepository
import es.jfechevarria.domain.picture.repositories.domain.PictureOrderBy
import es.jfechevarria.domain.result.AppResult

class GetPictureOrderByListUseCase(
    private val pictureRepository: PictureRepository
): AbstractUseCase<Unit, List<PictureOrderBy>>() {
    override suspend fun execute(params: Unit): AppResult<List<PictureOrderBy>> {
        val orderByList = mutableListOf(PictureOrderBy.POPULAR, PictureOrderBy.LATEST, PictureOrderBy.OLDEST)
        when (val value = pictureRepository.haveLocalSaved()) {
            is AppResult.Success -> {
                if (value.response) {
                    orderByList.add(0, PictureOrderBy.SAVED)
                }
            }
            else -> {}
        }
        return AppResult.Success(orderByList)
    }
}