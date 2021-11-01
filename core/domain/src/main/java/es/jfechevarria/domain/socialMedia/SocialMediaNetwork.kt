package es.jfechevarria.domain.socialMedia

data class SocialMediaNetwork(
    val type: SocialMediaTypes,
    val userName: String
) {
    val socialMediaURL = type.baseUrl.replace(":userName", userName)
}