package com.rachellimaa.features.channel.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.rachellimaa.features.R

class LoadStateAdapter : RecyclerView.Adapter<LoadStateAdapter.LoadStateViewHolder>() {

    var loadState: LoadState = LoadState.Done
        set(value) {
            when (field) {
                value -> {
                    notifyItemChanged(0)
                }
                is LoadState.Loading -> {
                    itemsCount = 0
                    notifyItemRemoved(0)
                }
                is LoadState.Done -> {
                    itemsCount = 1
                    notifyItemInserted(1)
                }
            }

            field = value
        }

    private var itemsCount: Int = 0

    inner class LoadStateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(VIEW_ID, parent, false)
    ) {
        fun bind(loadState: LoadState) {
            itemView.findViewById<ProgressBar>(R.id.catalog_loading).apply {
                visibility = if (LoadState.Loading == loadState) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LoadStateViewHolder(parent)
    override fun getItemViewType(position: Int): Int = VIEW_ID
    override fun getItemCount(): Int = itemsCount
    override fun onBindViewHolder(holder: LoadStateViewHolder, position: Int) {
        holder.bind(loadState)
    }

    companion object {
        private var VIEW_ID = R.layout.item_loading
    }
}