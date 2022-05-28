package com.interview.payments.presentation.chairs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.interview.payments.databinding.ItemFurnitureBinding
import com.interview.payments.domain.pojo.Furniture

class FurnitureAdapter : ListAdapter<FurnitureItem, FurnitureViewHolder>(FurnitureDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnitureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFurnitureBinding.inflate(inflater, parent, false)
        return FurnitureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FurnitureViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object FurnitureDiffCallback : DiffUtil.ItemCallback<FurnitureItem>() {
        override fun areItemsTheSame(oldItem: FurnitureItem, newItem: FurnitureItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FurnitureItem, newItem: FurnitureItem): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.description == newItem.description && oldItem.price == newItem.price
        }

    }
}