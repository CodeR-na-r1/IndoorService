package com.mrx.indoorservice.di

import android.hardware.SensorManager
import androidx.core.content.ContextCompat.getSystemService
import com.mrx.indoorservice.api.IndoorService
import org.koin.dsl.module

val appModule = module {
    single<IndoorService> {
        IndoorService
    }

    single<SensorManager> {
        getSystemService(get(), SensorManager::class.java) as SensorManager
    }
}