package com.example.kotlin2_l1.presentation.main

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

    val shopList = getShopItemListUseCase.getShopItemList()

    fun addShopItem(shopItem: ShopItem){
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem){
        editShopItemUseCase.editShopItem(shopItem.copy(isPicked = !shopItem.isPicked))
    }

    fun getShopItem(shopItemId: Int): ShopItem{
        return getShopItemUseCase.getShopItem(shopItemId)
    }
}