package es.jfechevarria.domain.pictureExif

import es.jfechevarria.domain.pictureExif.valueObjects.*

data class PictureExif(
    val make: MakeExifVO? = null,
    val model: ModelExifVO? = null,
    val exposure: ExposureExifVO? = null,
    val aperture: ApertureExifVO? = null,
    val name: NameExifVO? = null,
    val focalLength: FocalLengthExifVO? = null,
    val iso: IsoExifVO? = null
)