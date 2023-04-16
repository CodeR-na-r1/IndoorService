package com.mrx.indoorservice.domain.externalInterface

import androidx.lifecycle.LiveData
import com.mrx.indoorservice.domain.model.WiFiBeaconsEnvironmentInfo

interface WiFiBeaconManagerInterface {

    /* methods for config */

    fun setScanInterval(intervalValueMilliseconds: Long)   // config

    /* methods for scanning all wifi points */

    fun startAllScanning()

    fun getAllScanLastResult() : Collection<WiFiBeaconsEnvironmentInfo>

    fun getAllScanViewModel() : LiveData<Collection<WiFiBeaconsEnvironmentInfo>>

    fun stopAllScanning()

    /* methods for scanning specific wifi points */

    fun addSSIDFilterForSpecificScan(ssid: String)    // config

    fun addSSIDFilterForSpecificScan(ssid: Collection<String>)    // config

    fun setMaskFilterForSpecificScan(maskSSID: String)  // config

    fun deleteSSIDFilterForSpecificScan(ssid: String)   // config

    fun deleteSSIDFilterForSpecificScan(ssid: Collection<String>)   // config

    fun clearMaskFilterForSpecificScan()  // config

    fun clearSSIDFilter()   // config

    fun startSpecificScanning()

    fun getSpecificScanLastResult() : Collection<WiFiBeaconsEnvironmentInfo>

    fun getSpecificScanViewModel() : LiveData<Collection<WiFiBeaconsEnvironmentInfo>>

    fun stopSpecificScanning()
}