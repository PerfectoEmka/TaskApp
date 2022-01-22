package com.example.kotlin2_l1.domain

import java.io.Serializable

data class ShopItem(
    var name: String,
    val count: Int,
    var isPicked: Boolean,
    var id: Int = UNDEFINED_ID

    ) : Serializable {
    companion object{
        const val UNDEFINED_ID = -1
    }
}
