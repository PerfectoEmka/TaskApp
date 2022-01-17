package com.example.kotlin2_l1.data

import com.example.kotlin2_l1.domain.ShopItem
import com.example.kotlin2_l1.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun getShopItemList(): MutableList<ShopItem> {
        return this.shopList
    }

    override fun editShopItem(index: Int, shopItem: ShopItem) {
        shopList[index] = shopItem
    }

    override fun getShopItem(index: Int): ShopItem {
        return shopList[index]
    }
}