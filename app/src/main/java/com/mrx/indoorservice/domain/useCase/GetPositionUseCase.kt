package com.mrx.indoorservice.domain.useCase

import com.mrx.indoorservice.domain.externalInterface.BeaconManagerInterface
import com.mrx.indoorservice.domain.model.PositionInfo

NOT_IMPLEMENT

class GetPositionUseCase(val beaconManager: BeaconManagerInterface) {

    fun execute() : PositionInfo {
        // toDO
    }
}