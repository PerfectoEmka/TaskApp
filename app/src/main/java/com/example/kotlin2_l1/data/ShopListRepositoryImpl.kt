package com.example.kotlin2_l1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin2_l1.domain.ShopItem
import com.example.kotlin2_l1.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl: ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({a,b -> a.id.compareTo(b.id)})
    private var autoIncrementId = 0

    init {
        for (i in 0 until 100){
            val item =ShopItem ("Name $i", i, false)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        update()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        update()
    }

    override fun getShopItemList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { shopItem ->
            shopItem.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun update() {
        shopListLD.value = shopList.toList()
    }
}