package com.micbakos.floatingmap

import com.google.android.gms.maps.GoogleMap

internal class GoogleMapCallbacks(
    private val mapReadyOrchestrator: MapReadyOrchestrator
) : GoogleMap.OnMapLoadedCallback,
    GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraMoveCanceledListener,
    GoogleMap.OnCameraIdleListener {

    init {
        mapReadyOrchestrator.onMapReady()
    }

    override fun onMapLoaded() {
        mapReadyOrchestrator.onMapLoaded()
    }

    override fun onCameraMoveStarted(p0: Int) {

    }

    override fun onCameraMove() {

    }

    override fun onCameraMoveCanceled() {

    }

    override fun onCameraIdle() {

    }

}