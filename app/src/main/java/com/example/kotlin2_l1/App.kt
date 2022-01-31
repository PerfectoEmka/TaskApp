package com.example.kotlin2_l1

import android.app.Application
import androidx.room.Room
import com.example.kotlin2_l1.data.local.AppDataBase
import com.example.kotlin2_l1.data.local.ShopListRepositoryImpl
import com.example.kotlin2_l1.domain.repositories.ShopListRepository

class App: Application() {

    companion object {
        lateinit var appDatabase: AppDataBase
        lateinit var shopListRepository: ShopListRepository
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(this, AppDataBase::class.java, "database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        shopListRepository = ShopListRepositoryImpl
    }
}