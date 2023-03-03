package com.mrx.indoorservice.domain.useCase

import androidx.lifecycle.LiveData
import com.mrx.indoorservice.domain.externalInterface.BeaconManagerInterface
import com.mrx.indoorservice.domain.model.BeaconsEnvironmentInfo

class GetBeaconsEnvironmentUseCase(private val beaconManager: BeaconManagerInterface) {

    fun startRanging() {
        beaconManager.startRanging()
    }

    fun getRanging() : Collection<BeaconsEnvironmentInfo> {
        return beaconManager.getRanging()
    }

    fun getRangingViewModel() : LiveData<Collection<BeaconsEnvironmentInfo>> {
        return beaconManager.getRangingViewModel()
    }

    fun stopRanging() {
        beaconManager.stopRanging()
    }
}