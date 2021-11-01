package es.jfechevarria.domain.picture

import es.jfechevarria.domain.picture.valueObjects.*
import es.jfechevarria.domain.pictureExif.PictureExif
import es.jfechevarria.domain.pictureLocation.LocationPicture

data class Picture(
    val id: IdPictureVO,
    val description: DescriptionPictureVO?,
    val imageUrl: ImageURIPictureVO,
    val likesCount: LikesPictureVO,
    val creatorUserNamePictureVO: CreatorUserNamePictureVO,
    val cameraExif: PictureExif = PictureExif(),
    val location: LocationPicture = LocationPicture(),
    val isSaved: IsSavedPictureVO = IsSavedPictureVO(false)
)