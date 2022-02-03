package com.example.kotlin2_l1.di

import android.content.Context
import androidx.room.Room
import com.example.kotlin2_l1.data.local.AppDataBase
import com.example.kotlin2_l1.data.local.ShopDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase{
        return Room.databaseBuilder(context, AppDataBase::class.java, "database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideShopListDao(appDataBase: AppDataBase): ShopDao{
        return appDataBase.shopItemDao()
    }
}