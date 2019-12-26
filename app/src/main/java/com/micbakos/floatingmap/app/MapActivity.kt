package com.micbakos.floatingmap.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.micbakos.floatingmap.FloatingMap

class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val map = supportFragmentManager.findFragmentById(R.id.map) as FloatingMap
    }

}