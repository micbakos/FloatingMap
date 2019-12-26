package com.micbakos.floatingmap

class MapReadyOrchestrator(private val onReady: () -> Unit) {

    private var mapReady = false
    private var mapLoaded = false
    private var onReadyFired = false

    fun onMapReady() {
        mapReady = true

        if (mapLoaded && !onReadyFired) {
            fireReady()
        }
    }

    fun onMapLoaded() {
        mapLoaded = true

        if (mapReady && !onReadyFired) {
            fireReady()
        }
    }

    private fun fireReady() {
        onReadyFired = true
        onReady()
    }

}