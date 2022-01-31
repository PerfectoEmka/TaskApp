package com.example.kotlin2_l1.domain.use_cases

import com.example.kotlin2_l1.domain.models.ShopItem
import com.example.kotlin2_l1.domain.repositories.ShopListRepository

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }

}