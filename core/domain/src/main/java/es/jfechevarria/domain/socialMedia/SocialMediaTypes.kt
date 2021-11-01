package es.jfechevarria.domain.socialMedia

enum class SocialMediaTypes(val baseUrl: String) {
    TWITTER("https://twitter.com/:userName"),
    INSTAGRAM("https://instagram.com/:userName"),
    FACEBOOK("https://www.facebook.com/:userName")
}