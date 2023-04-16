package com.mrx.indoorservice.data.wifiBeaconManager

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WiFiScanService() : Service() {

    private val wifiManager: WifiManager by lazy { applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager }

    private val handler = Handler(Looper.getMainLooper())

    private val checkWiFiScheduler = Runnable {
        checkWifi()
    }

    private fun checkWifi() {
        wifiManager.startScan()

        dataScanResult.value = wifiManager.scanResults  // permissions granted by the user

        handler.postDelayed(checkWiFiScheduler, TIME_UPDATE.value!!)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // observing on input livedata for config service on runtime

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    companion object {
        /* fields for configure */
        val TIME_UPDATE = MutableLiveData<Long>(3000L)

        /* fields for read (observe) */
        private val dataScanResult = MutableLiveData<List<ScanResult>>()

        /* getters */
        fun getDataScanResult(): LiveData<List<ScanResult>> = dataScanResult

        fun startService(context: Context) {
            context.startService(Intent(context, WiFiScanService::class.java))
        }

        fun stopService(context: Context) {
            context.stopService(Intent(context, WiFiScanService::class.java))
        }
    }
}