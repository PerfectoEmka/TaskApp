package com.example.kotlin2_l1.domain.use_cases

import com.example.kotlin2_l1.domain.models.ShopItem
import com.example.kotlin2_l1.domain.repositories.ShopListRepository

class EditShopItemUseCase(private val repository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem) {
        return repository.editShopItem(shopItem)
    }
}