package com.example.kotlin2_l1.presentation.ui.activities.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2_l1.R
import com.example.kotlin2_l1.domain.models.ShopItem
import com.example.kotlin2_l1.utils.Constant
import com.example.kotlin2_l1.utils.ShopItemDiffCallback
import java.lang.RuntimeException

class ShopItemListAdapter
    : ListAdapter<ShopItem, ShopItemListAdapter.ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType){
            LAYOUT_ITEM_ENABLED -> R.layout.item_recycler_disabled
            LAYOUT_ITEM_DISABLED -> R.layout.item_recycler_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return ShopItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                    layout,
                    parent,
                    false
            ))
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.onBind(getItem(holder.absoluteAdapterPosition))
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isPicked){
            Constant.LAYOUT_ITEM_ENABLED
        } else Constant.LAYOUT_ITEM_DISABLED
    }

    inner class ShopItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvCount: TextView = itemView.findViewById(R.id.tv_count)

        fun onBind(shopItem: ShopItem){
            itemView.setOnLongClickListener{
                onShopItemLongClickListener?.invoke(shopItem)
                true
            }
            itemView.setOnClickListener{
                onShopItemClickListener?.invoke(shopItem)
            }
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
        }
    }

    companion object{
        const val LAYOUT_ITEM_ENABLED = 1
        const val LAYOUT_ITEM_DISABLED = 0
    }
}
