package com.mrx.indoorservice.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mrx.indoorservice.R
import com.mrx.indoorservice.api.IndoorService
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val indoorService: IndoorService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // todo test for indoorService

        indoorService.AzimuthManager.getAzimuthViewModel().observe(this) {
            findViewById<TextView>(R.id.textView).text = it.toString()
        }
    }

    override fun onResume() {
        super.onResume()

        indoorService.AzimuthManager.startListen()
    }

    override fun onPause() {
        super.onPause()

        indoorService.AzimuthManager.stopListen()
    }
}