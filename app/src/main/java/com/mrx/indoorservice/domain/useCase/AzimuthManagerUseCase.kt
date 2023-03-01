package com.mrx.indoorservice.domain.useCase

import androidx.lifecycle.LiveData
import com.mrx.indoorservice.domain.externalInterface.AzimuthManagerInterface

class AzimuthManagerUseCase(private val azimuthManager: AzimuthManagerInterface) {
    fun init() {
        azimuthManager.init()
    }

    fun getAzimuth() : Float? {
        return azimuthManager.getAzimuth()
    }

    fun getAzimuthViewModel() : LiveData<Float> {
        return azimuthManager.getAzimuthViewModel()
    }

    fun destroy() {
        azimuthManager.destroy()
    }
}