package com.mrx.indoorservice.data.bluetoothBeaconManager

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mrx.indoorservice.domain.externalInterface.BluetoothBeaconManagerInterface
import com.mrx.indoorservice.domain.model.BluetoothBeaconsEnvironmentInfo
import org.altbeacon.beacon.Beacon
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Region
import org.altbeacon.beacon.service.RunningAverageRssiFilter

class ALTBluetoothBeaconManagerImpl(private val context: Context) : BluetoothBeaconManagerInterface {

    private val value : MutableLiveData<Collection<BluetoothBeaconsEnvironmentInfo>> = MutableLiveData(arrayListOf<BluetoothBeaconsEnvironmentInfo>())

    private val beaconManager: BeaconManager = BeaconManager.getInstanceForApplication(context)
    private val region: Region = Region("all-beacons-region", null, null, null)
    private val observer: Observer<Collection<Beacon>> = Observer<Collection<Beacon>> { beacons ->
        val temp = ArrayList<BluetoothBeaconsEnvironmentInfo>()
        for (beacon in beacons){
            temp.add(BluetoothBeaconsEnvironmentInfo(beacon.bluetoothAddress, beacon.distance))
        }
        this.value.value = temp
    }

    init {
        BeaconManager.setRssiFilterImplClass(RunningAverageRssiFilter::class.java)
        RunningAverageRssiFilter.setSampleExpirationMilliseconds(5000L)
        beaconManager.beaconParsers.clear()
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"))
    }

    override fun startRanging() {
        beaconManager.startRangingBeacons(region)
        beaconManager.getRegionViewModel(region).rangedBeacons.observeForever(observer)
    }

    override fun getRanging(): Collection<BluetoothBeaconsEnvironmentInfo> {
        return this.value.value ?: ArrayList()
    }

    override fun getRangingViewModel(): LiveData<Collection<BluetoothBeaconsEnvironmentInfo>> {
        return this.value
    }

    override fun stopRanging() {
        beaconManager.getRegionViewModel(region).rangedBeacons.removeObserver(observer)
        beaconManager.stopRangingBeacons(region)
    }
}