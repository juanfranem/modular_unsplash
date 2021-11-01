package es.jfechevarria.listing_pictures.common.diffUtils

import androidx.recyclerview.widget.DiffUtil
import es.jfechevarria.domain.picture.repositories.domain.PictureOrderBy

class PictureOrderByDiffUtil(
    private val oldList: List<PictureOrderBy>,
    private val newList: List<PictureOrderBy>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }
}