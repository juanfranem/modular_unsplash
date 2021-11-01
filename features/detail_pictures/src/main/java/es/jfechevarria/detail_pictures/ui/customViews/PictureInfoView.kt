package es.jfechevarria.detail_pictures.ui.customViews

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import es.jfechevarria.detail_pictures.databinding.PictureInfoViewBinding
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.vivelibre.R

class PictureInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: PictureInfoViewBinding
    private var onBookmarkSelected: OnBookmarkSelected? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = PictureInfoViewBinding.inflate(LayoutInflater.from(context), this, false)
        binding.parentLayout.isVisible = false
        addView(binding.root)
    }

    fun setOnBookmarkSelectedListener(onBookmarkSelected: OnBookmarkSelected) {
        this.onBookmarkSelected = onBookmarkSelected
    }

    fun setPictureInfo(picture: Picture) {
        binding.author.text = resources.getString(R.string.author_name, picture.creatorUserNamePictureVO.value)
        binding.likes.text = resources.getQuantityString(R.plurals.likes_quantity, picture.likesCount.value, picture.likesCount.value)
        picture.description?.let {
            binding.title.isVisible = true
            binding.bio.isVisible = true
            binding.bio.text = it.value
        } ?: run {
            binding.title.isVisible = false
            binding.bio.isVisible = false
        }
        binding.share.setOnClickListener {
            Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, picture.imageUrl.value)
                type = "text/plain"
            }, null).let { context.startActivity(it) }

        }
        if (picture.isSaved.value) {
            binding.bookmark.setOnClickListener {
                onBookmarkSelected?.remove(picture)
            }
            binding.bookmark.setImageDrawable(resources.getDrawable(R.drawable.ic_bookmark_saved, context.applicationContext.theme))
        } else {
            binding.bookmark.setOnClickListener {
                onBookmarkSelected?.save(picture)
            }
            binding.bookmark.setImageDrawable(resources.getDrawable(R.drawable.ic_bookmark_empty, context.applicationContext.theme))
        }
        binding.parentLayout.isVisible = true
    }

    interface OnBookmarkSelected {
        fun save(picture: Picture)
        fun remove(picture: Picture)
    }
}