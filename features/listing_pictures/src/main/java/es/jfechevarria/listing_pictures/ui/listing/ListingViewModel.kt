package es.jfechevarria.listing_pictures.ui.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import es.jfechevarria.app_base.usecases.GetPictureOrderByListUseCase
import es.jfechevarria.app_base.viewModels.AbstractViewModel
import es.jfechevarria.domain.picture.repositories.domain.PictureOrderBy
import es.jfechevarria.domain.result.AppResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ListingViewModel(
    private val getPictureOrderByListUseCase: GetPictureOrderByListUseCase
): AbstractViewModel() {

    private val _viewPagerSelected = MutableLiveData<Int>()
    val viewPagerSelected: LiveData<Int> = _viewPagerSelected

    fun updatePageSelected(newSelection: Int) {
        _viewPagerSelected.postValue(newSelection)
    }

    private val orderByListData by lazy {
        val mutableOrderByList = MutableLiveData<List<PictureOrderBy>>()
        viewModelScope.launch {
            getPictureOrderByListUseCase(Unit)
                .onEach {
                    when (it) {
                        is AppResult.Success -> {
                            it.response.let { mutableOrderByList.postValue(it) }
                        }
                        else -> {}
                    }
                }.collect()
        }
        return@lazy mutableOrderByList
    }

    fun orderByList(): LiveData<List<PictureOrderBy>> = orderByListData

}