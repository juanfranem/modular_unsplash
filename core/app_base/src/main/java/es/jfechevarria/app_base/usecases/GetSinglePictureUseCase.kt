package es.jfechevarria.app_base.usecases

import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.repositories.PictureRepository
import es.jfechevarria.domain.picture.repositories.domain.PictureSingleRequest
import es.jfechevarria.domain.picture.repositories.domain.PicturesRequest
import es.jfechevarria.domain.result.AppResult


class GetSinglePictureUseCase (
    private val pictureRepository: PictureRepository
): AbstractUseCase<PictureSingleRequest, Picture>() {
    override suspend fun execute(params: PictureSingleRequest): AppResult<Picture> {
        return pictureRepository.find(params)
    }
}