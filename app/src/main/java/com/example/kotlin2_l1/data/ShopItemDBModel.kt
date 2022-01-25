package com.example.kotlin2_l1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class ShopItemDBModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val count: Int,
    val isPicked: Boolean
)
