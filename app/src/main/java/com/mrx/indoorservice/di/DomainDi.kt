package com.mrx.indoorservice.di

import com.mrx.indoorservice.domain.useCase.AzimuthManagerUseCase
import com.mrx.indoorservice.domain.useCase.GetBeaconsEnvironmentUseCase
import com.mrx.indoorservice.domain.useCase.GetPositionUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<AzimuthManagerUseCase> {
        AzimuthManagerUseCase(azimuthManager = get())
    }

    factory<GetBeaconsEnvironmentUseCase> {
        GetBeaconsEnvironmentUseCase(beaconManager = get())
    }

    factory<GetPositionUseCase> {
        GetPositionUseCase(positionManager = get())
    }
}