package com.mrx.indoorservice.data.azimuth

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mrx.indoorservice.domain.externalInterface.AzimuthManagerInterface
import com.mrx.indoorservice.domain.model.AzimuthInfo

class IMUAzimuthImpl(private val sManager: SensorManager) : AzimuthManagerInterface, SensorEventListener {

    private val value: MutableLiveData<Float> by lazy { MutableLiveData<Float>() }

    override fun startListen() {
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME)
    }

    override fun getAzimuth() : AzimuthInfo {
        return AzimuthInfo(this.value.value)
    }

    override fun getAzimuthViewModel() : LiveData<Float> {
        return this.value
    }

    override fun stopListen() {
        sManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            this.value.value = event.values[0]
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // no implementation
    }
}