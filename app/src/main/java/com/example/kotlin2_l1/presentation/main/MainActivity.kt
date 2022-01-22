package com.example.kotlin2_l1.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin2_l1.R
import com.example.kotlin2_l1.databinding.ActivityMainBinding
import com.example.kotlin2_l1.domain.ShopItem

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(R.id.container)
    private val viewModel: MainViewModel by viewModels()

    private var list = mutableListOf<ShopItem>()
    private lateinit var adapterShopItem: ShopItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservers()
    }

    private fun initObservers() {
        viewModel.shopList.observe(this, {
            this.list = it as MutableList<ShopItem>
            setupRv()
        })
    }

    private fun setupRv() {
        adapterShopItem = ShopItemAdapter()
        adapterShopItem.setList(list)
        binding.mainRecycler.apply {
            adapter = adapterShopItem
        }
        setUpSwipeListener(binding.mainRecycler)
    }

    private fun setUpSwipeListener(rv: RecyclerView){
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                list.removeAt(viewHolder.absoluteAdapterPosition)
                //adapterShopItem.setList(list)
                adapterShopItem.notifyItemRemoved(viewHolder.absoluteAdapterPosition)
                // ---вот с этим срабатывает анимация сдвига у рекуклера при удалении---
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }
}