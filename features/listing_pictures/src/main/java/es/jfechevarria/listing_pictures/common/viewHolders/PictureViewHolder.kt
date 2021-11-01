package es.jfechevarria.listing_pictures.common.viewHolders

import androidx.core.view.isVisible
import coil.load
import es.jfechevarria.app_base.viewHolders.AbstractViewHolder
import es.jfechevarria.domain.picture.Picture
import es.jfechevarria.listing_pictures.databinding.ItemPictureBinding

class PictureViewHolder(
    private val onItemClick: (picture: Picture) -> Unit,
    binding: ItemPictureBinding): AbstractViewHolder<Picture, ItemPictureBinding>(binding) {
    override fun bind(item: Picture) {
        binding.favouriteIcon.isVisible = item.isSaved.value
        binding.imageView.load(item.imageUrl.value)
        binding.imageView.setOnClickListener { onItemClick(item) }
    }
}