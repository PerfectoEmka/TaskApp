package com.example.kotlin2_l1.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.kotlin2_l1.domain.models.ShopItem
import com.example.kotlin2_l1.domain.repositories.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(private val shopDao : ShopDao)
    : ShopListRepository{

    private val mapper = ShopListMapper()

    /*init {
        for (i in 0 until 100){
            val item = ShopItem ("Name $i", i, false, i)
            addShopItem(item)
        }
    }*/

    override fun addShopItem(shopItem: ShopItem) {
        //App.appDatabase.shopItemDao().insertShopItem(mapper.mapEntityToDBModel(shopItem))
        shopDao.insertShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        //App.appDatabase.shopItemDao().deleteShopItem(mapper.mapEntityToDBModel(shopItem))
        shopDao.deleteShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override fun getShopItemList(): LiveData<List<ShopItem>> = Transformations.map(
        //App.appDatabase.shopItemDao().getShopList()
    shopDao.getShopList()
    ){
        mapper.mapListDBToListEntity(it)
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopDao.editShopItem(mapper.mapEntityToDBModel(shopItem))
        //App.appDatabase.shopItemDao().editShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override fun getShopItemByName(shopItemName: String): ShopItem {
        val shopItemDB = shopDao.getShopItemByName(shopItemName)
            /*App.appDatabase
            .shopItemDao()
            .getShopItemByName(shopItemName)*/

        return if (shopItemDB == null){
            ShopItem("Item not found", -1, false, -1)
        } else{
            mapper.mapDBModelToEntity(shopItemDB)
        }
    }

    override fun getShopItemById(shopItemId: Int): ShopItem {
        return mapper.mapDBModelToEntity(shopDao.getShopItemById(shopItemId))
    }
}