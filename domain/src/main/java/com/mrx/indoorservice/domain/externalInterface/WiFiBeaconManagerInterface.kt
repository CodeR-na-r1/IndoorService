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

    fun setSSIDForSpecificScan(ssid: String)    // config

    fun setSSIDForSpecificScan(ssid: Collection<String>)    // config

    fun startSpecificScanning()

    fun getSpecificScanLastResult()

    fun getSpecificScanViewModel()

    fun stopSpecificScanning()
}