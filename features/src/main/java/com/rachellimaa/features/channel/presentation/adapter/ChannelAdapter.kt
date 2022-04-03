package com.rachellimaa.features.channel.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachellimaa.features.R
import com.rachellimaa.features.channel.domain.model.ChannelModel

class ChannelAdapter : RecyclerView.Adapter<ChannelAdapter.CatalogChannelViewHolder>() {

    var items: MutableList<ChannelModel> = mutableListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)
            notifyItemRangeInserted(oldSize, newSize)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatalogChannelViewHolder(parent)
    override fun getItemViewType(position: Int): Int = VIEW_ID
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CatalogChannelViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CatalogChannelViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(VIEW_ID, parent, false)
    ) {
        fun bind(data: ChannelModel) {
            itemView.findViewById<TextView>(R.id.catalog_channel_name).apply {
                text = data.channelTitle
            }

            itemView.findViewById<TextView>(R.id.catalog_current_program).apply {
                text = data.currentProgram
            }

            itemView.findViewById<TextView>(R.id.catalog_next_program).apply {
                text = data.nextProgram
            }

            itemView.findViewById<ImageView>(R.id.catalog_channel_image).apply {
                loadImage(this, data.programImage) {
                    loadImage(this, data.channelImage)
                }
            }
        }

        private fun loadImage(imageView: ImageView, url: String, onError: () -> Unit = {}) {
            Glide.with(itemView.context)
                .load(url)
                .placeholder(R.drawable.ic_placeholder)
                .centerCrop()
                .error(onError())
                .into(imageView)
        }
    }

    companion object {
        private var VIEW_ID = R.layout.item_channel
    }
}