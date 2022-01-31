package com.example.kotlin2_l1.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlin2_l1.data.local.db_models.ShopItemDBModel

@Dao
interface ShopDao {

    @Query("SELECT * FROM shop_items")
    fun getShopList(): LiveData<List<ShopItemDBModel>>

    @Query("SELECT * FROM shop_items WHERE id =:shopItemId LIMIT 1")
    fun getShopItemById(shopItemId: Int): ShopItemDBModel

    @Query("SELECT * FROM shop_items WHERE name =:shopItemName LIMIT 1")
    fun getShopItemByName(shopItemName: String): ShopItemDBModel

    @Insert
    fun insertShopItem(shopItemDBModel: ShopItemDBModel)

    @Delete
    fun deleteShopItem(shopItemDBModel: ShopItemDBModel)

    @Update
    fun editShopItem(shopItemDBModel: ShopItemDBModel)
}