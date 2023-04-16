package com.mrx.indoorservice.data.wifiBeaconManager

import android.content.Context
import android.net.wifi.WifiManager
import com.mrx.indoorservice.domain.externalInterface.WiFiBeaconManagerInterface

NOT_IMPLEMENT

class WiFiBeaconManagerImpl(private val context: Context): WiFiBeaconManagerInterface {

    

    override fun setScanInterval() {
        TODO("Not yet implemented")
    }

    override fun startAllScanning() {
        TODO("Not yet implemented")
    }

    override fun getAllScanLastResult() {
        TODO("Not yet implemented")
    }

    override fun getAllScanViewModel() {
        TODO("Not yet implemented")
    }

    override fun stopAllScanning() {
        TODO("Not yet implemented")
    }

    override fun setSSIDFilterForSpecificScan(ssid: String) {
        TODO("Not yet implemented")
    }

    override fun setSSIDFilterForSpecificScan(ssid: Collection<String>) {
        TODO("Not yet implemented")
    }

    override fun setMaskFilterForSpecificScan(maskSSID: String) {
        TODO("Not yet implemented")
    }

    override fun deleteSSIDFilterForSpecificScan(ssid: String) {
        TODO("Not yet implemented")
    }

    override fun deleteSSIDFilterForSpecificScan(ssid: Collection<String>) {
        TODO("Not yet implemented")
    }

    override fun deleteMaskFilterForSpecificScan() {
        TODO("Not yet implemented")
    }

    override fun clearSSIDFilter() {
        TODO("Not yet implemented")
    }

    override fun startSpecificScanning() {
        TODO("Not yet implemented")
    }

    override fun getSpecificScanLastResult() {
        TODO("Not yet implemented")
    }

    override fun getSpecificScanViewModel() {
        TODO("Not yet implemented")
    }

    override fun stopSpecificScanning() {
        TODO("Not yet implemented")
    }
}