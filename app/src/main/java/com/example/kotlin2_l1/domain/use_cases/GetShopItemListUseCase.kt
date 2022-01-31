package com.example.kotlin2_l1.domain.use_cases

import com.example.kotlin2_l1.domain.repositories.ShopListRepository

class GetShopItemListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemList() = shopListRepository.getShopItemList()
}