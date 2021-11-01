package es.jfechevarria.listing_pictures.dii

import es.jfechevarria.listing_pictures.ui.listing.ListingViewModel
import es.jfechevarria.listing_pictures.ui.listingPage.ListingPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val listingModule = module {
    viewModel { ListingViewModel(get()) }
    viewModel { ListingPageViewModel(get()) }
}

val loadListingModule by lazy {
    val modules = listOf(listingModule)
    loadKoinModules(modules)
}