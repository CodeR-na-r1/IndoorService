package com.mrx.indoorservice.domain.useCase

import androidx.lifecycle.LiveData
import com.mrx.indoorservice.domain.externalInterface.AzimuthManagerInterface
import com.mrx.indoorservice.domain.model.AzimuthInfo

class AzimuthManagerUseCase(private val azimuthManager: AzimuthManagerInterface) {

    fun startListen() {
        azimuthManager.startListen()
    }

    fun getAzimuth() : AzimuthInfo {
        return azimuthManager.getAzimuth()
    }

    fun getAzimuthViewModel() : LiveData<Float> {
        return azimuthManager.getAzimuthViewModel()
    }

    fun stopListen() {
        azimuthManager.stopListen()
    }
}