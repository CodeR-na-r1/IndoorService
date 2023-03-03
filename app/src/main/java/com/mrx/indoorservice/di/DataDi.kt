package com.mrx.indoorservice.di

import com.mrx.indoorservice.data.azimuth.IMUAzimuthImpl
import com.mrx.indoorservice.data.beaconManager.ALTBeaconManagerImpl
import com.mrx.indoorservice.data.positionManager.TrilaterationPositionManagerImpl
import com.mrx.indoorservice.domain.externalInterface.AzimuthManagerInterface
import com.mrx.indoorservice.domain.externalInterface.BeaconManagerInterface
import com.mrx.indoorservice.domain.externalInterface.PositionManagerInterface
import org.koin.dsl.module

val dataModule = module {
    single<AzimuthManagerInterface> {
        IMUAzimuthImpl(sManager = get())
    }

    single<BeaconManagerInterface> {
        ALTBeaconManagerImpl(context = get())
    }

    single<PositionManagerInterface> {
        TrilaterationPositionManagerImpl(stateEnvironment = null)
    }
}