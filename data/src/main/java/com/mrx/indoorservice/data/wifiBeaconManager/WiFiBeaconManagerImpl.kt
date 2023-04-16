package com.mrx.indoorservice.data.wifiBeaconManager

import android.content.Context
import android.net.wifi.ScanResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mrx.indoorservice.domain.externalInterface.WiFiBeaconManagerInterface
import com.mrx.indoorservice.domain.model.WiFiBeaconsEnvironmentInfo

class WiFiBeaconManagerImpl(private val context: Context): WiFiBeaconManagerInterface {

    private val wifiData = MutableLiveData<Collection<WiFiBeaconsEnvironmentInfo>>()
    private val wifiFilteredData = MutableLiveData<Collection<WiFiBeaconsEnvironmentInfo>>()

    private val observerWiFiData = { it: List<ScanResult> ->
        wifiData.value = it.map { WiFiBeaconsEnvironmentInfo(it.SSID, it.level) }
    }

    private val filters = mutableListOf<String>()
    private var mask = ""

    private val observerWifiFilteredData = { it: List<ScanResult> ->
        // check definition

        if (mask == "" && filters.size == 0) {
            throw UninitializedPropertyAccessException("For SpecificScanning you need to set a mask or filter or all at once with help methods addSSIDFilterForSpecificScan() or setMaskFilterForSpecificScan()")
        }

        // copy raw data

        val res = it.toList()

        // process data by mask

        if (mask != "") {
            res.filter { it.BSSID.contains(mask) }
        }

        // process data by filters

        if (filters.size > 0) {
            res.filter { WiFiData ->
                var isHit = false
                filters.forEach { filter ->
                    if (WiFiData.SSID.contains(filter)) {
                        isHit = true
                    }
                }
                isHit
            }
        }

        // save data

        wifiFilteredData.value = res.map { WiFiBeaconsEnvironmentInfo(it.BSSID, it.level) }
    }

    override fun setScanInterval(intervalValueMilliseconds: Long) {
        WiFiScanService.TIME_UPDATE.value = intervalValueMilliseconds
    }

    override fun startAllScanning() {
        WiFiScanService.startService(context)
        WiFiScanService.getDataScanResult().observeForever(observerWiFiData)
    }

    override fun getAllScanLastResult(): Collection<WiFiBeaconsEnvironmentInfo> {
        return wifiData.value ?: ArrayList()
    }

    override fun getAllScanViewModel(): LiveData<Collection<WiFiBeaconsEnvironmentInfo>> {
        return wifiData
    }

    override fun stopAllScanning() {
        WiFiScanService.getDataScanResult().removeObserver(observerWiFiData)
        WiFiScanService.stopService(context)
    }

    override fun addSSIDFilterForSpecificScan(ssid: String) {
        filters.add(ssid)
    }

    override fun addSSIDFilterForSpecificScan(ssid: Collection<String>) {
        ssid.forEach { filters.add(it) }
    }

    override fun setMaskFilterForSpecificScan(maskSSID: String) {
        mask = maskSSID
    }

    override fun deleteSSIDFilterForSpecificScan(ssid: String) {
        filters.remove(ssid)
    }

    override fun deleteSSIDFilterForSpecificScan(ssid: Collection<String>) {
        ssid.forEach { filters.remove(it) }
    }

    override fun clearMaskFilterForSpecificScan() {
        mask = ""
    }

    override fun clearSSIDFilter() {
        filters.clear()
    }

    override fun startSpecificScanning() {
        WiFiScanService.startService(context)
        WiFiScanService.getDataScanResult().observeForever(observerWifiFilteredData)
    }

    override fun getSpecificScanLastResult(): Collection<WiFiBeaconsEnvironmentInfo> {
        return wifiFilteredData.value ?: ArrayList()
    }

    override fun getSpecificScanViewModel(): LiveData<Collection<WiFiBeaconsEnvironmentInfo>> {
        return wifiFilteredData
    }

    override fun stopSpecificScanning() {
        WiFiScanService.getDataScanResult().removeObserver(observerWifiFilteredData)
        WiFiScanService.stopService(context)
    }
}