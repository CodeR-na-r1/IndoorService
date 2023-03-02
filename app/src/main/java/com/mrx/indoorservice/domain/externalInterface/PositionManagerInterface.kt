package com.mrx.indoorservice.domain.externalInterface

import com.mrx.indoorservice.domain.model.EnvironmentInfo
import com.mrx.indoorservice.domain.model.PositionInfo

interface PositionManagerInterface {

    fun getPosition(environmentInfo: Collection<EnvironmentInfo>) : PositionInfo
}