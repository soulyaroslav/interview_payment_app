package com.interview.payments.presentation.card.select.adapter

import androidx.recyclerview.widget.RecyclerView
import com.interview.payments.databinding.ItemCardBinding
import com.interview.payments.domain.pojo.Card

class CardViewHolder(
    private val binding: ItemCardBinding,
    private val onClickAction: (Card) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentItem: Card? = null

    init {
        binding.root.setOnClickListener {
            currentItem?.let { onClickAction(it) }
        }
    }

    fun bind(item: Card) = with(binding) {
        currentItem = item
        cardNumberACTV.text = item.number
    }
}