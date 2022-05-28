package com.interview.payments.presentation.card.select.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.interview.payments.databinding.ItemCardBinding
import com.interview.payments.domain.pojo.Card

class CardAdapter(private val onClickAction: (Card) -> Unit) : ListAdapter<Card, CardViewHolder>(CardDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding, onClickAction)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object CardDiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean = oldItem == oldItem
        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean = oldItem == oldItem
    }
}