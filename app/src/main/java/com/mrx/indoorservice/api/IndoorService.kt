package com.mrx.indoorservice.api

import android.content.Context
import android.hardware.SensorManager
import androidx.core.content.ContextCompat.getSystemService
import com.mrx.indoorservice.data.azimuth.IMUAzimuthImpl
import com.mrx.indoorservice.data.bluetoothBeaconManager.ALTBluetoothBeaconManagerImpl
import com.mrx.indoorservice.data.positionManager.TrilaterationPositionManagerImpl
import com.mrx.indoorservice.data.wifiBeaconManager.WiFiBeaconManagerImpl
import com.mrx.indoorservice.domain.useCase.*

class IndoorService private constructor(context: Context)
{
    lateinit var AzimuthManager: AzimuthManagerUseCase
    lateinit var BluetoothBeaconsEnvironment: GetBluetoothBeaconsEnvironmentUseCase
    lateinit var WiFiBeaconsEnvironment: GetWiFiBeaconsEnvironmentUseCase
    val Position: GetPositionUseCase by lazy { GetPositionUseCase(TrilaterationPositionManagerImpl()) }
    val Mapper: MapperUseCase by lazy { MapperUseCase() }

    companion object {

        private var instance: IndoorService? = null

        fun getInstance(context: Context): IndoorService {
                return instance ?: synchronized(this) {
                    IndoorService(context).also {
                        it.AzimuthManager = AzimuthManagerUseCase(IMUAzimuthImpl(getSystemService(context, SensorManager::class.java) as SensorManager))
                        it.BluetoothBeaconsEnvironment = GetBluetoothBeaconsEnvironmentUseCase(ALTBluetoothBeaconManagerImpl(context = context))
                        it.WiFiBeaconsEnvironment = GetWiFiBeaconsEnvironmentUseCase(WiFiBeaconManagerImpl(context = context))

                        instance = it
                    }
                }
        }
    }
}