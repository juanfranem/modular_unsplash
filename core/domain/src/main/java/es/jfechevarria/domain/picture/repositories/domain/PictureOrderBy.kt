package es.jfechevarria.domain.picture.repositories.domain

enum class PictureOrderBy {
    SAVED, LATEST, OLDEST, POPULAR;

    override fun toString(): String {
        return when (this) {
            SAVED -> "saved"
            LATEST -> "latest"
            OLDEST -> "oldest"
            else -> "popular"
        }
    }
}