package com.example.kotlin2_l1.domain

class GetShopItemListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemList() = shopListRepository.getShopItemList()
}