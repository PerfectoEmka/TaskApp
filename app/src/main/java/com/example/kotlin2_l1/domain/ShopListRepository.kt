package com.example.kotlin2_l1.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopItemList(): MutableList<ShopItem>

    fun editShopItem(index: Int, shopItem: ShopItem)

    fun getShopItem(index: Int): ShopItem
}