package com.example.kotlin2_l1.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2_l1.R
import com.example.kotlin2_l1.domain.ShopItem
import com.example.kotlin2_l1.utils.Constant
import com.example.kotlin2_l1.utils.ShopItemDiffCallback

class ShopItemListAdapter
    : ListAdapter<ShopItem, ShopItemListAdapter.ShopItemViewHolder>(ShopItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return if (viewType == Constant.LAYOUT_ITEM_ENABLED){
            ShopItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recycler, parent, false)
            )
        } else {
            ShopItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recycler_disabled, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.onBind(getItem(holder.absoluteAdapterPosition))
    }

    override fun getItemViewType(position: Int): Int {
        return if (!getItem(position).isPicked){
            Constant.LAYOUT_ITEM_ENABLED
        } else Constant.LAYOUT_ITEM_DISABLED
    }

    fun removeShopItem(absolutePosition: Int) {
        val newList = ArrayList(currentList)
        newList.remove(getItem(absolutePosition))
        submitList(newList)
    }

    inner class ShopItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvCount: TextView = itemView.findViewById(R.id.tv_count)

        fun onBind(shopItem: ShopItem){

            itemView.setOnLongClickListener{
                val newList = ArrayList(currentList)

                newList[absoluteAdapterPosition] =
                    newList[absoluteAdapterPosition]
                        .copy(isPicked = !newList[absoluteAdapterPosition].isPicked)
                submitList(newList)
                return@setOnLongClickListener true
            }
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
        }
    }
}
