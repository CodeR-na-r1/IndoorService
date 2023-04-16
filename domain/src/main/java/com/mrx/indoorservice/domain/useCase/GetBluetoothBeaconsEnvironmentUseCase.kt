package com.mrx.indoorservice.domain.useCase

import androidx.lifecycle.LiveData
import com.mrx.indoorservice.domain.externalInterface.BluetoothBeaconManagerInterface
import com.mrx.indoorservice.domain.model.BluetoothBeaconsEnvironmentInfo

class GetBluetoothBeaconsEnvironmentUseCase(private val bluetoothBeaconManager: BluetoothBeaconManagerInterface) {

    fun startRanging() {
        bluetoothBeaconManager.startRanging()
    }

    fun getRanging() : Collection<BluetoothBeaconsEnvironmentInfo> {
        return bluetoothBeaconManager.getRanging()
    }

    fun getRangingViewModel() : LiveData<Collection<BluetoothBeaconsEnvironmentInfo>> {
        return bluetoothBeaconManager.getRangingViewModel()
    }

    fun stopRanging() {
        bluetoothBeaconManager.stopRanging()
    }
}