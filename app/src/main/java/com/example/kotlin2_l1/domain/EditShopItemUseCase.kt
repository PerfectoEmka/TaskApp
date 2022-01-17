package com.example.kotlin2_l1.domain

class EditShopItemUseCase(private val repository: ShopListRepository) {
    fun editShopItem(index: Int, shopItem: ShopItem) {
        return repository.editShopItem(index, shopItem)
    }
}