package es.jfechevarria.app_base.viewHolders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class AbstractViewHolder<R: Any, VB: ViewBinding>(val binding: VB)
    : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: R)
}