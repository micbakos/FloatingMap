package com.micbakos.floatingmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.SupportMapFragment

class FloatingMap : SupportMapFragment() {

    private lateinit var googleMapView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        googleMapView = super.onCreateView(inflater, viewGroup, bundle)
            ?: throw RuntimeException("Error inflating Google Map")

        return googleMapView
    }

    override fun getView(): View? = googleMapView

}