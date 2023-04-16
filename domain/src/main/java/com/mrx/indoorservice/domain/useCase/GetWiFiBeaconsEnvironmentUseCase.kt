package com.mrx.indoorservice.domain.useCase

import androidx.lifecycle.LiveData
import com.mrx.indoorservice.domain.externalInterface.WiFiBeaconManagerInterface
import com.mrx.indoorservice.domain.model.WiFiBeaconsEnvironmentInfo

class GetWiFiBeaconsEnvironmentUseCase(private val wifiBeaconManager: WiFiBeaconManagerInterface) {

    fun setScanInterval(intervalValueMilliseconds: Long) {
        wifiBeaconManager.setScanInterval(intervalValueMilliseconds = intervalValueMilliseconds)
    }

    fun startAllScanning() {
        wifiBeaconManager.startAllScanning()
    }

    fun getAllScanLastResult() : Collection<WiFiBeaconsEnvironmentInfo> {
        return wifiBeaconManager.getAllScanLastResult()
    }

    fun getAllScanViewModel() : LiveData<Collection<WiFiBeaconsEnvironmentInfo>> {
        return wifiBeaconManager.getAllScanViewModel()
    }

    fun stopAllScanning() {
        wifiBeaconManager.stopAllScanning()
    }

    fun addSSIDFilterForSpecificScan(ssid: String) {
        wifiBeaconManager.addSSIDFilterForSpecificScan(ssid = ssid)
    }

    fun addSSIDFilterForSpecificScan(ssid: Collection<String>) {
        wifiBeaconManager.addSSIDFilterForSpecificScan(ssid = ssid)
    }

    fun setMaskFilterForSpecificScan(maskSSID: String) {
        wifiBeaconManager.setMaskFilterForSpecificScan(maskSSID = maskSSID)
    }

    fun deleteSSIDFilterForSpecificScan(ssid: String) {
        wifiBeaconManager.deleteSSIDFilterForSpecificScan(ssid = ssid)
    }

    fun deleteSSIDFilterForSpecificScan(ssid: Collection<String>) {
        wifiBeaconManager.deleteSSIDFilterForSpecificScan(ssid = ssid)
    }

    fun clearMaskFilterForSpecificScan() {
        wifiBeaconManager.clearMaskFilterForSpecificScan()
    }

    fun clearSSIDFilter() {
        wifiBeaconManager.clearSSIDFilter()
    }

    fun startSpecificScanning() {
        wifiBeaconManager.startSpecificScanning()
    }

    fun getSpecificScanLastResult() : Collection<WiFiBeaconsEnvironmentInfo> {
        return wifiBeaconManager.getSpecificScanLastResult()
    }

    fun getSpecificScanViewModel() : LiveData<Collection<WiFiBeaconsEnvironmentInfo>> {
        return wifiBeaconManager.getSpecificScanViewModel()
    }

    fun stopSpecificScanning() {
        wifiBeaconManager.stopSpecificScanning()
    }
}