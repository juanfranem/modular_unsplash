package es.jfechevarria.domain.user

import es.jfechevarria.domain.user.valueObjects.*

data class User(
    val id: IdUserVO,
    val bio: BioUserVO,
    val avatar: AvatarUserVO,
    val location: LocationUserVO,
    val name: NameUserVO,
    val userName: UserNameUserVO,
    val socialMedias: RRSSUserVO
)