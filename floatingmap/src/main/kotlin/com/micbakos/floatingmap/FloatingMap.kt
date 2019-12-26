package com.micbakos.floatingmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.SupportMapFragment

class FloatingMap : SupportMapFragment() {

    private lateinit var googleMapView: View
    private lateinit var mapOverlay: MapOverlay

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

        return mapOverlay
    }

    override fun getView(): View? = googleMapView


}