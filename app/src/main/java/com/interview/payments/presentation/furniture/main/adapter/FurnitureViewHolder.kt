package com.interview.payments.presentation.furniture.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.interview.payments.databinding.ItemFurnitureBinding
import com.interview.payments.domain.pojo.Furniture
import com.interview.payments.ext.prepareFurnitureSpannable

class FurnitureViewHolder(
    private val binding: ItemFurnitureBinding,
    private val onClickAction: (Furniture) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentItem: Furniture? = null

    init {
        binding.root.setOnClickListener {
            currentItem?.let { item -> onClickAction(item) }
        }
    }

    fun bind(item: Furniture) = with(binding) {
        currentItem = item
        furnitureNameACTV.text = item.title
        furnitureDescriptionACTV.text = item.description
        furniturePriceACTV.prepareFurnitureSpannable(item)
        Glide.with(root.context)
            .load(item.image)
            .into(furnitureACIV)

    }
}