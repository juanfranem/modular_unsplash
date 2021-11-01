package es.jfechevarria.listing_pictures.ui.listingPage

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import es.jfechevarria.app_base.usecases.GetPicturesUseCase
import es.jfechevarria.app_base.viewModels.AbstractViewModel
import es.jfechevarria.domain.picture.repositories.domain.PictureOrderBy
import es.jfechevarria.listing_pictures.common.pagingDataSource.PicturePagingDataSource

class ListingPageViewModel(
    private val getPicturesUseCase: GetPicturesUseCase,
): AbstractViewModel() {
    fun getPicturesPagingFlow(orderBy: PictureOrderBy) = Pager(PagingConfig(pageSize = 10)) {
        PicturePagingDataSource(getPicturesUseCase, orderBy)
    }.flow.cachedIn(viewModelScope)
}