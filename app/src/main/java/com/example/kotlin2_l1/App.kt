package com.example.kotlin2_l1

import android.app.Application
import androidx.room.Room
import com.example.kotlin2_l1.data.AppDataBase

class App: Application() {

    companion object {
        lateinit var appDatabase: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(this, AppDataBase::class.java, "database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}