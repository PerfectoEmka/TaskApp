package com.example.kotlin2_l1.domain

class EditShopItemUseCase(private val repository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem) {
        return repository.editShopItem(shopItem)
    }
}