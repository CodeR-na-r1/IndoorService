package com.mrx.indoorservice.data.positionManager

import com.mrx.indoorservice.domain.externalInterface.PositionManagerInterface
import com.mrx.indoorservice.domain.model.EnvironmentInfo
import com.mrx.indoorservice.domain.model.PositionInfo
import com.mrx.indoorservice.domain.model.StateEnvironment

NOT_IMPLEMENT

class TrilaterationPositionManagerImpl(private val initPositions: Collection<StateEnvironment>) : PositionManagerInterface {

    override fun getPosition(environmentInfo: Collection<EnvironmentInfo>): PositionInfo {
        TODO("Not yet implemented")
    }

}