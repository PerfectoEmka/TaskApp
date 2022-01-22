package com.example.kotlin2_l1.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2_l1.R
import com.example.kotlin2_l1.domain.ShopItem
import com.example.kotlin2_l1.utils.Constant
import com.example.kotlin2_l1.utils.ShopItemDiffCallback

class ShopItemAdapter: RecyclerView.Adapter<ShopItemAdapter.ShopItemViewHolder>() {

    private var oldList = mutableListOf<ShopItem>()

    fun setList(newList: MutableList<ShopItem>){
        val diffUtil = ShopItemDiffCallback(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopItemViewHolder {

        return when (viewType) {
            Constant.LAYOUT_ITEM_ENABLED -> ShopItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_recycler,
                    parent,
                    false
                )
            )
            Constant.LAYOUT_ITEM_DISABLED -> ShopItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_recycler_disabled,
                    parent,
                    false
                )
            )
            else -> ShopItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_recycler,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (oldList[position].isPicked){
            Constant.LAYOUT_ITEM_DISABLED
        } else {
            Constant.LAYOUT_ITEM_ENABLED
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    inner class ShopItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvCont: TextView = itemView.findViewById(R.id.tv_count)

        fun onBind(position: Int) {
            itemView.setOnLongClickListener{
                oldList[absoluteAdapterPosition].isPicked = !oldList[absoluteAdapterPosition].isPicked
                notifyItemChanged(absoluteAdapterPosition)
                //setList(oldList)
                return@setOnLongClickListener true
            }
            tvName.text = oldList[position].name
            tvCont.text = oldList[position].count.toString()
        }
    }
}

