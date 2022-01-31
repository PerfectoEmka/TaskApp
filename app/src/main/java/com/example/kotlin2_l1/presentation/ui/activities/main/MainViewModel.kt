package com.example.kotlin2_l1.presentation.ui.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin2_l1.App
import com.example.kotlin2_l1.domain.models.ShopItem
import com.example.kotlin2_l1.domain.use_cases.*
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = App.shopListRepository

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopItemListUseCase = GetShopItemListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemByIdUseCase = GetShopItemByIdUseCase(repository)
    private val getShopItemByNameUseCase = GetShopItemByNameUseCase(repository)

    lateinit var shopList: LiveData<List<ShopItem>>
    val shopItem = MutableLiveData<ShopItem>()

    init {
        viewModelScope.launch {
            shopList = getShopItemListUseCase.getShopItemList()
        }
    }

    fun addShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun deleteShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun editShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            editShopItemUseCase.editShopItem(shopItem.copy(isPicked = !shopItem.isPicked))
        }
    }

    fun getShopItemById(shopItemId: Int) {
        viewModelScope.launch {
            shopItem.value = getShopItemByIdUseCase.getShopItemById(shopItemId)
        }
    }

    fun getShopItemByName(shopItemName: String){
        viewModelScope.launch {
            shopItem.value = getShopItemByNameUseCase.getShopItemByName(shopItemName)
        }
    }
}