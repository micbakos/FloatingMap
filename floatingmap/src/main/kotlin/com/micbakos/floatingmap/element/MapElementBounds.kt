package com.micbakos.floatingmap.element

import com.google.android.gms.maps.model.LatLng

data class MapElementBounds(
    val positions: List<LatLng>,
    val maxWidthRadius: Float,
    val maxHeightRadius: Float
)
