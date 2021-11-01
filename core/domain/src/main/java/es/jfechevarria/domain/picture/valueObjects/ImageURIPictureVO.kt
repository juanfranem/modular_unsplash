package es.jfechevarria.domain.picture.valueObjects

import android.webkit.URLUtil
import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.common.exceptions.EmptyStringException
import es.jfechevarria.domain.common.exceptions.UrlFormatException
import es.jfechevarria.domain.common.valueObjects.ValueObject
import java.net.URI

class ImageURIPictureVO(value: String): ValueObject<String>(value) {

    override val validationRules: List<Pair<Boolean, AppException>>
        get() = listOf(
            value.isEmpty() to EmptyStringException(this::class.java.toString()),
            !URLUtil.isValidUrl(value) to UrlFormatException(this::class.java.toString())
        )

}