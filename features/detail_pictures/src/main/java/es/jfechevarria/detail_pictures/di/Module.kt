package es.jfechevarria.detail_pictures.di

import es.jfechevarria.detail_pictures.ui.detailPicture.DetailPictureFragmentArgs
import es.jfechevarria.detail_pictures.ui.detailPicture.DetailPictureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val module = module {
    viewModel { navArgs -> DetailPictureViewModel(navArgs.get(), get(), get(), get()) }
}

val loadModule by lazy {
    loadKoinModules(module)
}