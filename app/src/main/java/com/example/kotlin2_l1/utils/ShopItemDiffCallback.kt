package com.example.kotlin2_l1.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlin2_l1.domain.ShopItem

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(
        oldItem: ShopItem,
        newItem: ShopItem
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: ShopItem,
        newItem: ShopItem
    ): Boolean = oldItem == newItem
}