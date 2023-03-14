package com.mrx.indoorservice.domain.useCase

import com.mrx.indoorservice.domain.model.BeaconsEnvironmentInfo
import com.mrx.indoorservice.domain.model.EnvironmentInfo

class MapperUseCase() {

    fun fromBeaconsEnvironmentInfoToEnvironmentInfo(beaconsEnvironmentInfo: Collection<BeaconsEnvironmentInfo>) : Collection<EnvironmentInfo>
    {
        return beaconsEnvironmentInfo.map { EnvironmentInfo(id = it.beaconId, distance = it.distance) }
    }
}