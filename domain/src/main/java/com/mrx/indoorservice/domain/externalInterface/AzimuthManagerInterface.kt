package com.mrx.indoorservice.domain.externalInterface

import androidx.lifecycle.LiveData
import com.mrx.indoorservice.domain.model.AzimuthInfo

interface AzimuthManagerInterface {

    fun startListen()

    fun getAzimuth() : AzimuthInfo

    fun getAzimuthViewModel() : LiveData<Float>

    fun stopListen()
}