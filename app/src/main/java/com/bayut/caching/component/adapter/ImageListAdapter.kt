package com.bayut.caching.component.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bayut.caching.callbacks.OnRecyclerItemClickCallback
import com.bayut.caching.component.model.ResultsItem
import com.bayut.caching.databinding.HomeListItemBinding


/**
 * Adapter for the [RecyclerView] in [ImageListAdapter].
 */
class ImageListAdapter(val callback: OnRecyclerItemClickCallback<ResultsItem>) :
    ListAdapter<ResultsItem, ImageListAdapter.ViewHolder>(DiffTypeCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            HomeListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val documents = getItem(position)
        holder.apply {
            bind(documents)
            itemView.tag = documents
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(
        private val binding: HomeListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResultsItem) {
            binding.apply {
                image = item
                itemView.setOnClickListener {
                    callback.onRecyclerItemClicked(adapterPosition, itemView, item)
                }
                executePendingBindings()
            }
        }
    }
}

private class DiffTypeCallback : DiffUtil.ItemCallback<ResultsItem>() {

    override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem.imageIds == newItem.imageIds
    }

    override fun areContentsTheSame(
        oldItem: ResultsItem,
        newItem: ResultsItem
    ): Boolean {
        return oldItem == newItem
    }
}