package com.mrx.indoorservice.domain.externalInterface

import com.mrx.indoorservice.domain.model.EnvironmentInfo
import com.mrx.indoorservice.domain.model.PositionInfo
import com.mrx.indoorservice.domain.model.StateEnvironment

interface PositionManagerInterface {

    fun setEnvironment(stateEnvironment: Collection<StateEnvironment>)

    fun getPosition(environmentInfo: Collection<EnvironmentInfo>) : PositionInfo
}