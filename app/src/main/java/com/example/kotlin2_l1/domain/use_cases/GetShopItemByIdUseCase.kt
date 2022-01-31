package com.example.kotlin2_l1.domain.use_cases

import com.example.kotlin2_l1.domain.repositories.ShopListRepository

class GetShopItemByIdUseCase(private val repository: ShopListRepository) {
    fun getShopItemById(shopItemId: Int) = repository.getShopItemById(shopItemId)
}