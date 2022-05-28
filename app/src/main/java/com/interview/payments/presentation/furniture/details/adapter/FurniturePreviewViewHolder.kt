package com.interview.payments.presentation.furniture.details.adapter

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.interview.payments.databinding.ItemFurniturePreviewBinding

class FurniturePreviewViewHolder(private val binding: ItemFurniturePreviewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(@DrawableRes preview: Int) = with(binding) {
        Glide.with(root.context)
            .load(preview)
            .into(furniturePreviewACIV)
    }
}