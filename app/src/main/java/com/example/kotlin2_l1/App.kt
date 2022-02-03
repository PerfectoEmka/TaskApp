package com.example.kotlin2_l1

import android.app.Application
import androidx.room.Room
import com.example.kotlin2_l1.data.local.AppDataBase
import com.example.kotlin2_l1.data.local.ShopListRepositoryImpl
import com.example.kotlin2_l1.domain.repositories.ShopListRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

}