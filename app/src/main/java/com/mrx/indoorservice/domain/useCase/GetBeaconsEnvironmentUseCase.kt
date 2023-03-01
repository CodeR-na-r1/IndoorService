package com.mrx.indoorservice.domain.useCase

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mrx.indoorservice.domain.externalInterface.BeaconManagerInterface
import com.mrx.indoorservice.domain.model.BeaconsEnvironmentInfo

NOT_IMPLEMENT

class GetBeaconsEnvironmentUseCase(val beaconManager: BeaconManagerInterface) {

    fun execute(context: Context, owner: LifecycleOwner, funObserver: Observer<Collection<BeaconsEnvironmentInfo>>) {
        // toDO
    }
}