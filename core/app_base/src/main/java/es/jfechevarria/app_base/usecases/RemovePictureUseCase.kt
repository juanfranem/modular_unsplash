package es.jfechevarria.app_base.usecases

import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.repositories.PictureRepository
import es.jfechevarria.domain.result.AppResult


class RemovePictureUseCase (
    private val pictureRepository: PictureRepository
): AbstractUseCase<Picture, Unit>() {
    override suspend fun execute(params: Picture): AppResult<Unit> {
        return pictureRepository.remove(params)
    }
}