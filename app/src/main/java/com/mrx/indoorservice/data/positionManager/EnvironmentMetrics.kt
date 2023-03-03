package com.mrx.indoorservice.data.positionManager

import com.mrx.indoorservice.domain.model.Point

data class EnvironmentMetrics(val position: Point<Double>, val distance: Double)