package es.jfechevarria.domain.user.valueObjects

import android.webkit.URLUtil
import es.jfechevarria.domain.socialMedia.SocialMediaNetwork
import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.common.exceptions.EmptyStringException
import es.jfechevarria.domain.common.exceptions.UrlFormatException
import es.jfechevarria.domain.common.valueObjects.ValueObject

class RRSSUserVO(value: List<SocialMediaNetwork>): ValueObject<List<SocialMediaNetwork>>(value) {

    override val validationRules: List<Pair<Boolean, AppException>>
        get() = listOf(
            value.any { it.userName.isEmpty() } to EmptyStringException(this::class.java.toString()),
            value.any { !URLUtil.isValidUrl(it.socialMediaURL) } to UrlFormatException(this::class.java.toString())
        )

}