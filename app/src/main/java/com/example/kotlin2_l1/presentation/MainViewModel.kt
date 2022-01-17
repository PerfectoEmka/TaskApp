package com.example.kotlin2_l1.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin2_l1.data.ShopListRepositoryImpl
import com.example.kotlin2_l1.domain.*

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopItemListUseCase = GetShopItemListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    val shopList = MutableLiveData<MutableList<ShopItem>>()

    fun addShopItem(shopItem: ShopItem){
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun getShopItemList(){
        val list = getShopItemListUseCase.getShopItemList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopItem(index: Int, shopItem: ShopItem){
        editShopItemUseCase.editShopItem(index, shopItem)
    }

    fun getShopItem(index: Int){
        val shopItem = getShopItemUseCase.getShopItem(index)
        Log.e("TAG", "getShopItem (ViewModel) : $shopItem")
    }
}