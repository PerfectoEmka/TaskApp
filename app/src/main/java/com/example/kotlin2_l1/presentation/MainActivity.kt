package com.example.kotlin2_l1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin2_l1.R
import com.example.kotlin2_l1.databinding.ActivityMainBinding
import com.example.kotlin2_l1.domain.ShopItem
import java.lang.IndexOutOfBoundsException

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(R.id.container)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
    }

    private fun initListeners() {
        binding.btnDelete.setOnClickListener{
            deleteItem()
        }

        binding.btnStart.setOnClickListener{
            addShopItem()
        }

        binding.btnEdit.setOnClickListener{
            editShopItem()
        }

        binding.btnGetList.setOnClickListener{
            getShopItemList()
        }

        binding.btnGetItem.setOnClickListener{
            getShopItem()
        }
    }

    private fun getShopItem() {
        val shopItem = viewModel.getShopItem(0)
        Log.e("TAG", "getShopItem (MainActivity): $shopItem")
    }

    private fun getShopItemList() {
        viewModel.getShopItemList()
        var count = 0
        viewModel.shopList.value?.forEach { item ->
            count++
            Log.e("TAG", "getShopItemList (MainActivity): $item $count")
        }
        Log.e("TAG", "----------------------------------------")
    }

    private fun editShopItem() {
       viewModel.editShopItem(0, ShopItem("Emirlan", 3, true))
    }

    private fun deleteItem() {
        viewModel.shopList.value?.get(0)?.let {
            viewModel.deleteShopItem(it)
        }
    }

    private fun addShopItem(){
        viewModel.addShopItem(ShopItem("Potato", 2, false))
    }
}