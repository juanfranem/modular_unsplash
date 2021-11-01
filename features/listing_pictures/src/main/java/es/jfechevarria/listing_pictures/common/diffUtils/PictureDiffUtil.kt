package es.jfechevarria.listing_pictures.common.diffUtils

import androidx.recyclerview.widget.DiffUtil
import es.jfechevarria.domain.picture.Picture

object PictureDiffUtil: DiffUtil.ItemCallback<Picture>() {
    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.id.value == newItem.id.value
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.id.value == newItem.id.value
    }
}