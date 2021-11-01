package es.jfechevarria.app_base.usecases

import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.repositories.PictureRepository
import es.jfechevarria.domain.picture.repositories.domain.PicturesRequest
import es.jfechevarria.domain.result.AppResult


class GetPicturesUseCase (
    private val pictureRepository: PictureRepository
): AbstractUseCase<PicturesRequest, List<Picture>>() {
    override suspend fun execute(params: PicturesRequest): AppResult<List<Picture>> {
        return pictureRepository.get(params)
    }
}