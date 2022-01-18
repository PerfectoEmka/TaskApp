package com.example.kotlin2_l1.data

import android.util.Log
import com.example.kotlin2_l1.domain.ShopItem
import com.example.kotlin2_l1.domain.ShopListRepository
import java.lang.IndexOutOfBoundsException
import java.util.*

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

    override fun getShopItemList(): MutableList<ShopItem>? {
        if (shopList.isNotEmpty()){
            return shopList.toList() as MutableList<ShopItem>
        }
        return null
    }

    override fun editShopItem(index: Int, shopItem: ShopItem) {
        try {
            shopList[index] = shopItem
        } catch (e: IndexOutOfBoundsException){
            Log.e("TAG", "editShopItem (ShopListRepoImpl): IndexOutOfBounds")
        }
    }

    override fun getShopItem(index: Int): ShopItem? {
        return try {
            shopList[index]
        } catch (e: IndexOutOfBoundsException){
            Log.e("TAG", "getShopItem (ShopListRepoImpl): IndexOutOfBounds")
            null
        }
    }
}