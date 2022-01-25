package com.example.kotlin2_l1.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShopItemDBModel::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun shopItemDao(): ShopDao
}