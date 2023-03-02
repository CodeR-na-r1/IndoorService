package com.mrx.indoorservice.domain.externalInterface

import androidx.lifecycle.LiveData
import com.mrx.indoorservice.domain.model.BeaconsEnvironmentInfo

interface BeaconManagerInterface {

    fun startRanging()

    fun getRanging() : ArrayList<BeaconsEnvironmentInfo>

    fun getRangingViewModel() : LiveData<ArrayList<BeaconsEnvironmentInfo>>

    fun stopRanging()
}