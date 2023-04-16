package com.mrx.indoorservice.api

import android.content.Context
import android.hardware.SensorManager
import androidx.core.content.ContextCompat.getSystemService
import com.mrx.indoorservice.data.azimuth.IMUAzimuthImpl
import com.mrx.indoorservice.data.bluetoothBeaconManager.ALTBluetoothBeaconManagerImpl
import com.mrx.indoorservice.data.positionManager.TrilaterationPositionManagerImpl
import com.mrx.indoorservice.domain.useCase.AzimuthManagerUseCase
import com.mrx.indoorservice.domain.useCase.GetBluetoothBeaconsEnvironmentUseCase
import com.mrx.indoorservice.domain.useCase.GetPositionUseCase
import com.mrx.indoorservice.domain.useCase.MapperUseCase

object IndoorService
{
    private var init: Boolean = false

    lateinit var AzimuthManager: AzimuthManagerUseCase
    lateinit var BeaconsEnvironment: GetBluetoothBeaconsEnvironmentUseCase
    val Position: GetPositionUseCase by lazy { GetPositionUseCase(TrilaterationPositionManagerImpl()) }
    val Mapper: MapperUseCase by lazy { MapperUseCase() }

    fun getInstance(context: Context): IndoorService {
        if (!init) {
            this.AzimuthManager = AzimuthManagerUseCase(IMUAzimuthImpl(getSystemService(context, SensorManager::class.java) as SensorManager))
            this.BeaconsEnvironment = GetBluetoothBeaconsEnvironmentUseCase(ALTBluetoothBeaconManagerImpl(context))
            this.init = true
        }
        return this
    }
}