package es.jfechevarria.remote.domain.getPicture

import es.jfechevarria.domain.pictureExif.PictureExif
import es.jfechevarria.domain.pictureExif.valueObjects.*

internal data class Exif(
    val aperture: String?,
    val exposure_time: String?,
    val focal_length: String?,
    val iso: String?,
    val make: String?,
    val model: String?,
    val name: String?
) {
    fun toDomain(): PictureExif {
        return PictureExif(
            if (make == null) null else MakeExifVO(make),
            if (model == null) null else ModelExifVO(model),
            if (exposure_time == null) null else ExposureExifVO(exposure_time),
            if (aperture == null) null else ApertureExifVO(aperture),
            if (name == null) null else NameExifVO(name),
            if (focal_length == null) null else FocalLengthExifVO(focal_length),
            if (iso == null) null else IsoExifVO(iso)
        )
    }
}