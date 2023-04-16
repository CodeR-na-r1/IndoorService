package com.mrx.indoorservice.domain.useCase

import com.mrx.indoorservice.domain.model.BluetoothBeaconsEnvironmentInfo
import com.mrx.indoorservice.domain.model.EnvironmentInfo

class MapperUseCase() {

    fun fromBeaconsEnvironmentInfoToEnvironmentInfo(beaconsEnvironmentInfo: Collection<BluetoothBeaconsEnvironmentInfo>) : Collection<EnvironmentInfo>
    {
        return beaconsEnvironmentInfo.map { EnvironmentInfo(id = it.beaconId, distance = it.distance) }
    }
}