package es.jfechevarria.remote

import es.jfechevarria.remote.domain.getPicture.GetSinglePictureResponse
import es.jfechevarria.remote.domain.getPictures.GetPicturesResponse
import es.jfechevarria.remote.domain.getUserInformation.CompleteUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ApiService {
    @GET(ApiConstants.GET_PICTURES)
    suspend fun getPictures(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String
    ): GetPicturesResponse

    @GET(ApiConstants.GET_PICTURE)
    suspend fun getPicture(@Path("id") id: String): GetSinglePictureResponse

    @GET(ApiConstants.GET_USER)
    suspend fun getUserInformation(@Path("username") username: String): CompleteUserResponse
}