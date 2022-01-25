package com.example.kotlin2_l1.domain

class GetShopItemUseCase(private val repository: ShopListRepository) {
    fun getShopItem(shopItemId: Int) = repository.getShopItem(shopItemId)
}