package com.example.kotlin2_l1.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopItemList(): LiveData<List<ShopItem>>

    fun getShopItem(shopItemId: Int): ShopItem

    fun editShopItem(shopItem: ShopItem)

    fun update()
}