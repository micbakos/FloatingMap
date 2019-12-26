package com.micbakos.floatingmap.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.micbakos.floatingmap.FloatingMap
import com.micbakos.floatingmap.FloatingMapCallbacks

class MapActivity : AppCompatActivity(), FloatingMapCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val map = supportFragmentManager.findFragmentById(R.id.map) as FloatingMap
        map.floatingMapCallbacks = this
    }

    override fun onMapReady() {
        Log.d("MapActivity", "Map Ready")
    }

}