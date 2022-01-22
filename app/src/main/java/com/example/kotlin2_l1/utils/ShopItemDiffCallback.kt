package com.example.kotlin2_l1.utils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.kotlin2_l1.domain.ShopItem

open class ShopItemDiffCallback(
    private val oldList: List<ShopItem>,
    private val newList: List<ShopItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(oldItemPosition, newItemPosition)
    }
}