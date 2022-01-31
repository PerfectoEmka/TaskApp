package com.example.kotlin2_l1.domain.repositories

import androidx.lifecycle.LiveData
import com.example.kotlin2_l1.domain.models.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopItemList(): LiveData<List<ShopItem>>

    fun getShopItemById(shopItemId: Int): ShopItem

    fun editShopItem(shopItem: ShopItem)

    fun getShopItemByName(shopItemName: String): ShopItem
}