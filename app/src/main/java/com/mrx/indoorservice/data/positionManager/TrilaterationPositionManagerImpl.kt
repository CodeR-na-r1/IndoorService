package com.mrx.indoorservice.data.positionManager

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver
import com.lemmingapex.trilateration.TrilaterationFunction
import com.mrx.indoorservice.data.positionManager.models.EnvironmentMetrics
import com.mrx.indoorservice.domain.externalInterface.PositionManagerInterface
import com.mrx.indoorservice.domain.model.EnvironmentInfo
import com.mrx.indoorservice.domain.model.Point
import com.mrx.indoorservice.domain.model.PositionInfo
import com.mrx.indoorservice.domain.model.StateEnvironment
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer

class TrilaterationPositionManagerImpl(private var stateEnvironment: Collection<StateEnvironment>? = null) : PositionManagerInterface {

    override fun setEnvironment(stateEnvironment: Collection<StateEnvironment>) {
        this.stateEnvironment = stateEnvironment
    }

    override fun getPosition(environmentInfo: Collection<EnvironmentInfo>): PositionInfo {
        val environmentMetrics = this.mapEnvironmentInfoToEnvironmentMetrics(environmentInfo)

        val positions = preparePositions(environmentMetrics = environmentMetrics)
        val distances = prepareDistances(environmentMetrics = environmentMetrics)

        val nllss = NonLinearLeastSquaresSolver(TrilaterationFunction(positions, distances), LevenbergMarquardtOptimizer())

        val optimum = nllss.solve()

        val result = optimum.point.toArray()

        return PositionInfo(Point(x = result[0], y = result[1]))
    }

    private fun mapEnvironmentInfoToEnvironmentMetrics(environmentInfo: Collection<EnvironmentInfo>) : Collection<EnvironmentMetrics> {
        val res = ArrayList<EnvironmentMetrics>()

        environmentInfo.forEach { eInfo ->
            val el = this.stateEnvironment?.find { eState -> eState.id == eInfo.id }

            if (el != null) { res.add(EnvironmentMetrics(el.position, eInfo.distance)) }
        }

        return res
    }

    private fun preparePositions(environmentMetrics: Collection<EnvironmentMetrics>) : Array<DoubleArray> {
        return environmentMetrics.map { doubleArrayOf(it.position.x, it.position.y) }.toTypedArray()
    }

    private fun prepareDistances(environmentMetrics: Collection<EnvironmentMetrics>) : DoubleArray {
        return environmentMetrics.map { it.distance }.toTypedArray().toDoubleArray()
    }
}