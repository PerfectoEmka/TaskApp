package com.example.kotlin2_l1.domain.use_cases

import com.example.kotlin2_l1.domain.models.ShopItem
import com.example.kotlin2_l1.domain.repositories.ShopListRepository

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}