package es.jfechevarria.detail_pictures.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import es.jfechevarria.app_base.adapters.AbstractAdapter
import es.jfechevarria.app_base.viewHolders.AbstractViewHolder
import es.jfechevarria.detail_pictures.databinding.ItemExifBinding

class ExifAdapter: AbstractAdapter<Pair<Int, String>, ItemExifBinding, ExifAdapter.ExifViewHolder>(
    object: DiffUtil.ItemCallback<Pair<Int, String>>() {
        override fun areItemsTheSame(
            oldItem: Pair<Int, String>,
            newItem: Pair<Int, String>
        ): Boolean = oldItem.first == newItem.first

        override fun areContentsTheSame(
            oldItem: Pair<Int, String>,
            newItem: Pair<Int, String>
        ): Boolean = oldItem.second == newItem.second
    }
) {

    inner class ExifViewHolder(binding: ItemExifBinding): AbstractViewHolder<Pair<Int, String>, ItemExifBinding>(binding) {
        override fun bind(item: Pair<Int, String>) {
            binding.title.setText(item.first)
            binding.value.text = item.second
        }

    }

    override fun onCreateBindingHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ExifViewHolder {
        return ExifViewHolder(ItemExifBinding.inflate(inflater, parent, false))
    }

}