package com.example.kotlin2_l1.data.local

import com.example.kotlin2_l1.data.local.db_models.ShopItemDBModel
import com.example.kotlin2_l1.domain.models.ShopItem

class ShopListMapper {

    fun mapEntityToDBModel(shopItem: ShopItem) = ShopItemDBModel(
        id = shopItem.id,
        name = shopItem.name,
        isPicked = shopItem.isPicked,
        count = shopItem.count
    )

    fun mapDBModelToEntity(shopItemDB: ShopItemDBModel) = ShopItem(
        id = shopItemDB.id,
        name = shopItemDB.name,
        isPicked = shopItemDB.isPicked,
        count = shopItemDB.count
    )

    fun mapListDBToListEntity(list: List<ShopItemDBModel>) = list.map {
        mapDBModelToEntity(it)
    }
}