package com.mrx.indoorservice.api

import com.mrx.indoorservice.domain.useCase.AzimuthManagerUseCase
import com.mrx.indoorservice.domain.useCase.GetBeaconsEnvironmentUseCase
import com.mrx.indoorservice.domain.useCase.GetPositionUseCase

class IndoorService(val AzimuthManager: AzimuthManagerUseCase,
                    val BeaconsEnvironment: GetBeaconsEnvironmentUseCase,
                    val Position: GetPositionUseCase)
{
}