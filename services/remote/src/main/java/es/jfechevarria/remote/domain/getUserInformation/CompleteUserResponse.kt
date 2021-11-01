package es.jfechevarria.remote.domain.getUserInformation

import es.jfechevarria.domain.socialMedia.SocialMediaNetwork
import es.jfechevarria.domain.socialMedia.SocialMediaTypes
import es.jfechevarria.domain.user.User
import es.jfechevarria.domain.user.valueObjects.*
import es.jfechevarria.remote.domain.common.Social

internal data class CompleteUserResponse(
    val bio: String,
    val first_name: String,
    val id: String,
    val instagram_username: String?,
    val last_name: String,
    val location: String,
    val name: String,
    val profile_image: ProfileImage,
    val social: Social,
    val twitter_username: String?,
    val username: String
) {
    fun toDomain(): User {
        val mutableSocialMedias = mutableListOf<SocialMediaNetwork>()

        if (!twitter_username.isNullOrEmpty()) {
            mutableSocialMedias.add(SocialMediaNetwork(SocialMediaTypes.TWITTER, twitter_username))
        } else if (!social.twitter_username.isNullOrEmpty()) {
            mutableSocialMedias.add(SocialMediaNetwork(SocialMediaTypes.TWITTER, social.twitter_username))
        }

        if (!instagram_username.isNullOrEmpty()) {
            mutableSocialMedias.add(SocialMediaNetwork(SocialMediaTypes.INSTAGRAM, instagram_username))
        } else if (!social.instagram_username.isNullOrEmpty()) {
            mutableSocialMedias.add(SocialMediaNetwork(SocialMediaTypes.INSTAGRAM, social.instagram_username))
        }

        return User(
            IdUserVO(id),
            BioUserVO(bio),
            AvatarUserVO(profile_image.medium),
            LocationUserVO(location),
            NameUserVO(name),
            UserNameUserVO(username),
            RRSSUserVO(mutableSocialMedias)
        )
    }
}

