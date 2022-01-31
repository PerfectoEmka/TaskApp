package com.example.kotlin2_l1.presentation.ui.activities.add_shopItem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin2_l1.R
import com.example.kotlin2_l1.databinding.ActivityAddShopItemBinding
import com.example.kotlin2_l1.domain.models.ShopItem
import com.example.kotlin2_l1.presentation.ui.activities.main.MainViewModel

class AddShopItemActivity : AppCompatActivity() {

    private val binding: ActivityAddShopItemBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shop_item)

        initView()
        initListeners()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener{
            addShopItem()
        }
    }

    private fun addShopItem() {
        if (binding.etName.text.isNotEmpty() &&
            binding.etCount.text.isNotEmpty() &&
            binding.etId.text.isNotEmpty()){

            viewModel.addShopItem(
                ShopItem(
                binding.etName.text.toString(),
                Integer.parseInt(binding.etCount.text.toString()),
                false,
                Integer.parseInt(binding.etId.text.toString()))
            )
            finish()
        }
    }

    private fun initView() {

    }
}