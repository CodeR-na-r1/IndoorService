package com.mrx.indoorservice.data.positionManager.models

import com.mrx.indoorservice.domain.model.Point

data class EnvironmentMetrics(val position: Point<Double>, val distance: Double)