package com.mrx.indoorservice.domain.externalInterface

interface WiFiBeaconManagerInterface {

    /* methods for config */

    fun setScanInterval()   // config

    /* methods for scanning all wifi points */

    fun startAllScanning()

    fun getAllScanLastResult()

    fun getAllScanViewModel()

    fun stopAllScanning()

    /* methods for scanning specific wifi points */

    fun setSSIDFilterForSpecificScan(ssid: String)    // config

    fun setSSIDFilterForSpecificScan(ssid: Collection<String>)    // config

    fun setMaskFilterForSpecificScan(maskSSID: String)  // config

    fun deleteSSIDFilterForSpecificScan(ssid: String)   // config

    fun deleteSSIDFilterForSpecificScan(ssid: Collection<String>)   // config

    fun deleteMaskFilterForSpecificScan()  // config

    fun clearSSIDFilter()   // config

    fun startSpecificScanning()

    fun getSpecificScanLastResult()

    fun getSpecificScanViewModel()

    fun stopSpecificScanning()
}