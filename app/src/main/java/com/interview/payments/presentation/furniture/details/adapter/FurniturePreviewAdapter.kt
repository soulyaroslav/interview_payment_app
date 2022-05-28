package com.interview.payments.presentation.furniture.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.interview.payments.databinding.ItemFurniturePreviewBinding

class FurniturePreviewAdapter : ListAdapter<Int, FurniturePreviewViewHolder>(FurniturePreviewDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurniturePreviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFurniturePreviewBinding.inflate(inflater, parent, false)
        return FurniturePreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FurniturePreviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object FurniturePreviewDiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == oldItem
        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == oldItem
    }
}