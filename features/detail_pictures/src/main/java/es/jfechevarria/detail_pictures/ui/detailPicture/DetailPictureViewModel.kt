package es.jfechevarria.detail_pictures.ui.detailPicture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import es.jfechevarria.app_base.usecases.GetSinglePictureUseCase
import es.jfechevarria.app_base.usecases.RemovePictureUseCase
import es.jfechevarria.app_base.usecases.SavePictureUseCase
import es.jfechevarria.app_base.viewModels.SafeArgsAbstractViewModel
import es.jfechevarria.domain.common.exceptions.AppException
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.picture.repositories.domain.PictureSingleRequest
import es.jfechevarria.domain.picture.valueObjects.IsSavedPictureVO
import es.jfechevarria.domain.result.AppResult
import es.jfechevarria.domain.state.AppState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailPictureViewModel(
    private val pictureId: String,
    private val getSinglePictureUseCase: GetSinglePictureUseCase,
    private val removePictureUseCase: RemovePictureUseCase,
    private val savePictureUseCase: SavePictureUseCase
): SafeArgsAbstractViewModel() {

    private val mutablePicture = MutableLiveData<AppState<Picture>>()
    val picture: LiveData<AppState<Picture>> = mutablePicture

    init {
        loadPicture()
    }

    fun loadPicture() {
        viewModelScope.launch {
            getSinglePictureUseCase(PictureSingleRequest(pictureId))
                .catch {
                    mutablePicture.postValue(AppState.Exception(it as AppException))
                }
                .onEach {
                when (val response = it) {
                    is AppResult.Loading -> {
                        mutablePicture.postValue(AppState.Idle)
                    }
                    is AppResult.Success -> {
                        response.response.let { picture ->
                            mutablePicture.postValue(AppState.Data(picture))
                        }
                    }
                    is AppResult.Failure -> {
                        mutablePicture.postValue(AppState.Exception(response.cause))
                    }
                }
            }.collect()

        }
    }

    fun savePicture(picture: Picture) {
        viewModelScope.launch {
            savePictureUseCase(picture)
                .catch {
                    mutablePicture.postValue(AppState.Exception(it.cause as AppException))
                }
                .onEach {
                when (it) {
                    is AppResult.Loading -> {
                        mutablePicture.postValue(AppState.Idle)
                    }
                    is AppResult.Success -> {
                        mutablePicture.postValue(AppState.Data(picture.copy(isSaved = IsSavedPictureVO(true))))
                    }
                    is AppResult.Failure -> {
                        mutablePicture.postValue(AppState.Exception(it.cause))
                    }
                }
            }.collect()
        }
    }

    fun removePicture(picture: Picture) {
        viewModelScope.launch {
            removePictureUseCase(picture)
                .catch {
                    mutablePicture.postValue(AppState.Exception(it.cause as AppException))
                }
                .onEach {
                when (it) {
                    is AppResult.Loading -> {
                        mutablePicture.postValue(AppState.Idle)
                    }
                    is AppResult.Success -> {
                        mutablePicture.postValue(AppState.Data(picture.copy(isSaved = IsSavedPictureVO(false))))
                    }
                    is AppResult.Failure -> {
                        mutablePicture.postValue(AppState.Exception(it.cause))
                    }
                }
            }.collect()
        }
    }

}