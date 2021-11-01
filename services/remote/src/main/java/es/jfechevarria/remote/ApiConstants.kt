package es.jfechevarria.remote

internal object ApiConstants {
    const val GET_PICTURES = "photos"
    const val GET_PICTURE = "$GET_PICTURES/{id}"

    const val GET_USER = "users/{username}"
    const val GET_USER_PICTURES = "$GET_USER/photos"
}