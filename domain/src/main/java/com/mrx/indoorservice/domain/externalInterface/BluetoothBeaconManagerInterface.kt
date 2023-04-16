package com.mrx.indoorservice.domain.externalInterface

import androidx.lifecycle.LiveData
import com.mrx.indoorservice.domain.model.BluetoothBeaconsEnvironmentInfo

interface BluetoothBeaconManagerInterface {

    fun startRanging()

    fun getRanging() : Collection<BluetoothBeaconsEnvironmentInfo>

    fun getRangingViewModel() : LiveData<Collection<BluetoothBeaconsEnvironmentInfo>>

    fun stopRanging()
}