package es.jfechevarria.remote.domain.common

import es.jfechevarria.remote.domain.getPictures.ProfileImage

internal data class SimpleUser(
    val bio: String,
    val first_name: String,
    val id: String,
    val instagram_username: String,
    val last_name: String,
    val location: String,
    val name: String,
    val profile_image: ProfileImage,
    val social: Social,
    val twitter_username: String,
    val username: String
)