package com.example.kotlin2_l1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin2_l1.data.local.db_models.ShopItemDBModel

@Database(entities = [ShopItemDBModel::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun shopItemDao(): ShopDao
}