package es.jfechevarria.remote.di

import es.jfechevarria.domain.picture.dataSources.RemotePictureDataSource
import es.jfechevarria.domain.user.dataSources.UserRemoteDataSource
import es.jfechevarria.remote.ApiService
import es.jfechevarria.remote.Server
import es.jfechevarria.remote.dataSource.PictureDataSourceImpl
import es.jfechevarria.remote.dataSource.UserDataSourceImpl
import es.jfechevarria.remote.di.RemoteBindModule.provideApiService
import es.jfechevarria.remote.di.RemoteBindModule.provideBaseUrl
import es.jfechevarria.remote.di.RemoteBindModule.provideOkHttpClient
import es.jfechevarria.remote.di.RemoteBindModule.provideRetrofit
import es.jfechevarria.remote.di.RemoteModule.bindRemotePictureDataSource
import es.jfechevarria.remote.di.RemoteModule.bindRemoteUserDataSource
import es.jfechevarria.remote.interceptor.UnsplashInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    single { UnsplashInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), provideBaseUrl()) }

    single { provideApiService(get()) }

    factory { bindRemotePictureDataSource(get()) }
    factory { bindRemoteUserDataSource(get()) }
}

internal object RemoteModule {
    fun bindRemotePictureDataSource(apiService: ApiService)
        = PictureDataSourceImpl(apiService) as RemotePictureDataSource
    fun bindRemoteUserDataSource(apiService: ApiService)
        = UserDataSourceImpl(apiService) as UserRemoteDataSource
}


internal object RemoteBindModule {
    fun provideBaseUrl() = Server.BASE_URL
    fun provideOkHttpClient(unsplashInterceptor: UnsplashInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .addInterceptor(unsplashInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}