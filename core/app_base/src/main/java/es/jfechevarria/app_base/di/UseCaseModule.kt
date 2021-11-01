package es.jfechevarria.app_base.di

import es.jfechevarria.app_base.repositories.PictureRepositoryImpl
import es.jfechevarria.app_base.repositories.UserRepositoryImpl
import es.jfechevarria.app_base.usecases.*
import es.jfechevarria.domain.picture.repositories.PictureRepository
import es.jfechevarria.domain.user.repositories.UserRepository
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPicturesUseCase(get()) }
    factory { GetSinglePictureUseCase(get()) }
    factory { GetUserInformationUseCase(get()) }
    factory { RemovePictureUseCase(get()) }
    factory { SavePictureUseCase(get()) }
    factory { GetPictureOrderByListUseCase(get()) }
}

val repositoryModule = module {
    factory<PictureRepository> {PictureRepositoryImpl(get(), get())}
    factory<UserRepository> {UserRepositoryImpl(get())}
}
