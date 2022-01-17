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

        viewModel.shopList.observe(this, {
            it.forEach { item ->
                Log.e("TAG", "list: $item")
                Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show()
            }
        })
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
        try {
            viewModel.getShopItem(0)
        } catch (e: IndexOutOfBoundsException){
            Log.e("TAG", "getShopItem: ${e.message}")
        }
    }

    private fun getShopItemList() {
        viewModel.getShopItemList()
    }

    private fun editShopItem() {
        viewModel.shopList.observe(this, {
           try {
               viewModel.editShopItem(0, ShopItem("Emirlan", 3, true))
           } catch (e: IndexOutOfBoundsException){
               Log.e("TAG", "editShopItem : ${e.message}")
           }
        })
    }

    private fun deleteItem() {
        viewModel.shopList.observe(this, {
            if (it.isNotEmpty()){
                viewModel.deleteShopItem(it.first())
            }
        })
    }

    private fun addShopItem(){
        viewModel.addShopItem(ShopItem("Potato", 2, false))
    }
}