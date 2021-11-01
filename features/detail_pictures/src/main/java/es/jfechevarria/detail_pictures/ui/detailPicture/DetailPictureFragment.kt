package es.jfechevarria.detail_pictures.ui.detailPicture

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import coil.load
import es.jfechevarria.app_base.fragments.AbstractFragment
import es.jfechevarria.vivelibre.R
import es.jfechevarria.detail_pictures.databinding.FragmentDetailPictureBinding
import es.jfechevarria.detail_pictures.di.loadModule
import es.jfechevarria.detail_pictures.ui.customViews.PictureInfoView
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.domain.state.AppState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailPictureFragment: AbstractFragment<FragmentDetailPictureBinding>(FragmentDetailPictureBinding::inflate),
    PictureInfoView.OnBookmarkSelected {

    private fun inject() = loadModule
    private val navArgs by navArgs<DetailPictureFragmentArgs>()
    private val viewModel by viewModel<DetailPictureViewModel> { parametersOf(arguments?.getString(PICTURE_ID)) }


    companion object {
        private const val PICTURE_ID = "picture_id"

        fun instance(pictureId: String):DetailPictureFragment {
            return DetailPictureFragment().apply {
                arguments = Bundle().apply {
                    putString(PICTURE_ID, pictureId)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.picture.observe(viewLifecycleOwner) { state ->
                binding.progress.isVisible = state.loading
                binding.errorView.isVisible = false
                when (state) {
                    is AppState.Data -> {
                        bindPicture(state.response)
                    }
                    is AppState.Exception -> {
                        binding.errorView.isVisible = false
                        binding.errorView.setMessage(state.cause.message.orEmpty())
                    }
                    else -> {}
                }
            }
        }
        binding.errorView.setOnClickListener {
            viewModel.loadPicture()
        }
        binding.infoView.setOnBookmarkSelectedListener(this)
    }

    private fun bindPicture(picture: Picture) {
        binding.image.load(picture.imageUrl.value)
        binding.infoView.setPictureInfo(picture)
        binding.exifView.setExif(picture.cameraExif)
        binding.mapView.setLocation(picture.location)
    }

    override fun save(picture: Picture) {
        viewModel.savePicture(picture)
    }

    override fun remove(picture: Picture) {
        viewModel.removePicture(picture)
    }
}
