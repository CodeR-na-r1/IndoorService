package com.mrx.indoorservice.data.beaconManager

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mrx.indoorservice.domain.externalInterface.BeaconManagerInterface
import com.mrx.indoorservice.domain.model.BeaconsEnvironmentInfo
import org.altbeacon.beacon.Beacon
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.Region
import org.altbeacon.beacon.service.RunningAverageRssiFilter

class ALTBeaconManagerImpl(private val context: Context) : BeaconManagerInterface {

    private val value : MutableLiveData<Collection<BeaconsEnvironmentInfo>> = MutableLiveData(arrayListOf<BeaconsEnvironmentInfo>())

    private val beaconManager: BeaconManager = BeaconManager.getInstanceForApplication(context)
    private val region: Region = Region("all-beacons-region", null, null, null)
    private val observer: Observer<Collection<Beacon>> = Observer<Collection<Beacon>> { beacons ->
        val temp = ArrayList<BeaconsEnvironmentInfo>()
        for (beacon in beacons){
            temp.add(BeaconsEnvironmentInfo(beacon.bluetoothAddress, beacon.distance))
        }
        this.value.value = temp
    }

    init {
        BeaconManager.setRssiFilterImplClass(RunningAverageRssiFilter::class.java)
        RunningAverageRssiFilter.setSampleExpirationMilliseconds(5000L)
    }

    override fun startRanging() {
        beaconManager.startRangingBeacons(region)
        beaconManager.getRegionViewModel(region).rangedBeacons.observeForever(observer)
    }

    override fun getRanging(): Collection<BeaconsEnvironmentInfo> {
        return this.value.value ?: ArrayList()
    }

    override fun getRangingViewModel(): LiveData<Collection<BeaconsEnvironmentInfo>> {
        return this.value
    }

    override fun stopRanging() {
        beaconManager.getRegionViewModel(region).rangedBeacons.removeObserver(observer)
        beaconManager.stopRangingBeacons(region)
    }
}