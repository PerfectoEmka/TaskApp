package com.example.kotlin2_l1.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val isPicked: Boolean,
    var id: Int = UNDEFINED_ID

    ) {
    companion object{
        const val UNDEFINED_ID = -1
    }
}
