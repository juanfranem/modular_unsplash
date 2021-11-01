package es.jfechevarria.listing_pictures.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import es.jfechevarria.app_base.adapters.AbstractPagingAdapter
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.listing_pictures.databinding.ItemPictureBinding
import es.jfechevarria.listing_pictures.common.diffUtils.PictureDiffUtil
import es.jfechevarria.listing_pictures.common.viewHolders.PictureViewHolder

class PictureAdapter(
    private val onItemClick: (picture: Picture) -> Unit
): AbstractPagingAdapter<Picture, ItemPictureBinding, PictureViewHolder>(PictureDiffUtil) {
    override fun onCreateBindingHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): PictureViewHolder {
        return PictureViewHolder(onItemClick, ItemPictureBinding.inflate(inflater, parent, false))
    }
}