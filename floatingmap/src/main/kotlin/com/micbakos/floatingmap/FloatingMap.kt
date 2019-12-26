package com.micbakos.floatingmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class FloatingMap : SupportMapFragment() {

    private lateinit var googleMapView: View
    private lateinit var mapOverlay: MapOverlay
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        googleMapView = super.onCreateView(inflater, viewGroup, bundle)
            ?: throw RuntimeException("Error inflating Google Map")

        mapOverlay = MapOverlay(requireContext()).apply {
            addView(googleMapView)
        }

        getMapAsync { map ->
            this.googleMap = map

            with(map) {
                val googleMapCallbacks = GoogleMapCallbacks()

                setOnCameraIdleListener(googleMapCallbacks)
                setOnCameraMoveCanceledListener(googleMapCallbacks)
                setOnCameraMoveStartedListener(googleMapCallbacks)
                setOnCameraMoveListener(googleMapCallbacks)
                setOnMapLoadedCallback(googleMapCallbacks)
            }

            with(map.uiSettings) {
                isZoomGesturesEnabled = true
                isRotateGesturesEnabled = false
                isTiltGesturesEnabled = false
                isCompassEnabled = false
                isIndoorLevelPickerEnabled = false
                isMapToolbarEnabled = false
                isMyLocationButtonEnabled = false
                isZoomControlsEnabled = false
            }
        }

        return mapOverlay
    }

    override fun getView(): View? = googleMapView


}