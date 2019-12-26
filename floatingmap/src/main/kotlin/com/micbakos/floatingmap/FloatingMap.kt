package com.micbakos.floatingmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class FloatingMap : SupportMapFragment() {

    private lateinit var wrappedFragmentView: View
    private lateinit var mapOverlay: MapOverlay
    private lateinit var googleMap: GoogleMap

    var floatingMapCallbacks: FloatingMapCallbacks? = null
    var padding = MapPadding()
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        wrappedFragmentView = super.onCreateView(inflater, viewGroup, bundle)
            ?: throw RuntimeException("Error inflating Google Map")

        mapOverlay = MapOverlay(requireContext()).apply {
            addView(wrappedFragmentView)
        }

        initializeGoogleMap()
        return mapOverlay
    }

    override fun getView(): View? = wrappedFragmentView

    private fun initializeGoogleMap() {
        getMapAsync { map ->
            this.googleMap = map

            with(map) {
                val mapReadyOrchestrator = MapReadyOrchestrator {
                    floatingMapCallbacks?.onMapReady()
                }
                val googleMapCallbacks = GoogleMapCallbacks(mapReadyOrchestrator)

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
    }

    private fun setPadding(
        left: Int = padding.left,
        top: Int = padding.top,
        right: Int = padding.right,
        bottom: Int = padding.bottom
    ) {
        padding = padding.copy(
            left = left,
            top = top,
            right = right,
            bottom = bottom
        )

        googleMap.setPadding(top, left, right, bottom)
    }
}