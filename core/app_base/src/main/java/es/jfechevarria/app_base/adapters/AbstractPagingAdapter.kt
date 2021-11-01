package es.jfechevarria.app_base.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import es.jfechevarria.app_base.viewHolders.AbstractViewHolder

abstract class AbstractPagingAdapter<R: Any, VB: ViewBinding, VH: AbstractViewHolder<R, VB>>(
    diffUtil: DiffUtil.ItemCallback<R>
): PagingDataAdapter<R, VH>(diffUtil) {

    abstract fun onCreateBindingHolder(inflater: LayoutInflater,
                                 parent: ViewGroup): VH

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        return onCreateBindingHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}