package com.mrx.indoorservice.domain.externalInterface

import androidx.lifecycle.LiveData

interface AzimuthManagerInterface {

    fun init()

    fun getAzimuth() : Float?

    fun getAzimuthViewModel() : LiveData<Float>

    fun destroy()
}