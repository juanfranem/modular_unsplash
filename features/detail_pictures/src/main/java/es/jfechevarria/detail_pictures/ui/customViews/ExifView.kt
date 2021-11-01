package es.jfechevarria.detail_pictures.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import es.jfechevarria.vivelibre.R
import es.jfechevarria.detail_pictures.common.ExifAdapter
import es.jfechevarria.detail_pictures.databinding.ExifViewBinding
import es.jfechevarria.domain.pictureExif.PictureExif

class ExifView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ExifViewBinding
    private lateinit var adapter: ExifAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ExifViewBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)
    }

    fun setExif(cameraExif: PictureExif) {
        val exifData = mutableListOf<Pair<Int, String>>()
        cameraExif.make?.let {
            exifData.add(R.string.make_title to it.value)
        }

        cameraExif.model?.let {
            exifData.add(R.string.model_title to it.value)
        }

        cameraExif.name?.let {
            exifData.add(R.string.name_title to it.value)
        }

        cameraExif.exposure?.let {
            exifData.add(R.string.exposure_title to it.value)
        }

        cameraExif.aperture?.let {
            exifData.add(R.string.aperture_title to it.value)
        }

        cameraExif.focalLength?.let {
            exifData.add(R.string.focal_length_title to it.value)
        }

        cameraExif.iso?.let {
            exifData.add(R.string.iso_title to it.value)
        }

        if (exifData.any { it.second.isNotEmpty() }) {
            binding.root.isVisible = true
            adapter = ExifAdapter()
            binding.recyclerView.adapter = adapter
            adapter.submitList(exifData.filter { it.second.isNotEmpty() })
        }
    }
}