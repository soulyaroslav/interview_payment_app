package com.interview.payments.presentation.chairs.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.interview.payments.databinding.ItemFurnitureBinding

class FurnitureViewHolder(private val binding: ItemFurnitureBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FurnitureItem) = with(binding) {
        furnitureNameACTV.text = item.title
        furnitureDescriptionACTV.text = item.description
        furniturePriceACTV.text = item.price
        Glide.with(root.context)
            .load(item.image)
            .into(furnitureACIV)

    }
}