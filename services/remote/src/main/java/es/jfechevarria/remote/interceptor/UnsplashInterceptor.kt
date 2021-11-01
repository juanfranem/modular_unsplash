package es.jfechevarria.remote.interceptor

import es.jfechevarria.remote.Server
import okhttp3.Interceptor
import okhttp3.Response

internal class UnsplashInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .header("Accept-Version", "v1")
            .header("Authorization", "Client-ID ${Server.ACCESS_KEY}")
            .build()
        return chain.proceed(request)
    }
}