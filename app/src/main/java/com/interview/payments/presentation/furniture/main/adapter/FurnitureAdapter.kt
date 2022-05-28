package com.interview.payments.presentation.furniture.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.interview.payments.databinding.ItemFurnitureBinding
import com.interview.payments.domain.pojo.Furniture

class FurnitureAdapter(
    private val onClickAction: (Furniture) -> Unit
) : ListAdapter<Furniture, FurnitureViewHolder>(FurnitureDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnitureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFurnitureBinding.inflate(inflater, parent, false)
        return FurnitureViewHolder(binding, onClickAction)
    }

    override fun onBindViewHolder(holder: FurnitureViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object FurnitureDiffCallback : DiffUtil.ItemCallback<Furniture>() {
        override fun areItemsTheSame(oldItem: Furniture, newItem: Furniture): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Furniture, newItem: Furniture): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.description == newItem.description && oldItem.price == newItem.price
        }

    }
}