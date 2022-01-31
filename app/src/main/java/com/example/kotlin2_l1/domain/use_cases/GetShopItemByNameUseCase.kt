package com.example.kotlin2_l1.domain.use_cases

import com.example.kotlin2_l1.domain.repositories.ShopListRepository

class GetShopItemByNameUseCase(private val repository: ShopListRepository) {
    fun getShopItemByName(shopItemName: String) = repository.getShopItemByName(shopItemName)
}