package com.mrx.indoorservice.domain.useCase

import com.mrx.indoorservice.domain.externalInterface.PositionManagerInterface
import com.mrx.indoorservice.domain.model.EnvironmentInfo
import com.mrx.indoorservice.domain.model.PositionInfo
import com.mrx.indoorservice.domain.model.StateEnvironment

class GetPositionUseCase(private val positionManager: PositionManagerInterface) {

    fun setEnvironment(stateEnvironment: Collection<StateEnvironment>) {
        positionManager.setEnvironment(stateEnvironment = stateEnvironment)
    }

    fun getPosition(environmentInfo: Collection<EnvironmentInfo>) : PositionInfo {
        return positionManager.getPosition(environmentInfo = environmentInfo)
    }
}