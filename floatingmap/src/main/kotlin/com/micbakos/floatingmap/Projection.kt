package com.micbakos.floatingmap

import android.content.Context
import android.graphics.Rect
import com.google.android.gms.maps.model.LatLng
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.sin

class Projection(context: Context) {

    companion object {
        private const val EARTH_RADIUS = 6378137
        private const val EARTH_CIRCUMFERENCE = 2 * PI * EARTH_RADIUS
        private const val MIN_LAT = -85.05112878
        private const val MAX_LAT = 85.05112878
        private const val MIN_LNG = -180.0
        private const val MAX_LNG = 180.0
    }

    private val tileSize = context.resources.getDimensionPixelSize(R.dimen.map_tile_size)
    private val northEastPoint = doubleArrayOf(0.0, 0.0)
    private var zoom: Float = 0f

    fun updateVisibleRegion(
        width: Int,
        height: Int,
        padding: Rect,
        centerLat: Double,
        centerLng: Double,
        zoom: Float
    ) {
        this.zoom = zoom

        val frustumHeight = height - padding.top - padding.bottom
        val frustumWidth = width - padding.left - padding.right

        val globalX = toGlobalX(centerLng, zoom)
        val globalY = toGlobalY(centerLat, zoom)

        northEastPoint[0] = globalX - (frustumWidth / 2.0f) - padding.left
        northEastPoint[1] = globalY - (frustumHeight / 2.0f) - padding.top
    }

    fun projectX(longitude: Double): Float {
        val globalX = toGlobalX(longitude, zoom)

        return (globalX - northEastPoint[0]).toFloat()
    }

    fun projectY(latitude: Double): Float {
        val globalY = toGlobalY(latitude, zoom)

        return (globalY - northEastPoint[1]).toFloat()
    }

    private fun toGlobalX(longitude: Double, zoom: Float): Double {
        val lng = longitude.coerceIn(MIN_LNG, MAX_LNG)

        val x = (lng + 180.0) / 360.0

        val mapSize = mapSize(zoom)
        return (x * mapSize + 0.5).coerceIn(0.0, mapSize - 1)
    }

    private fun toGlobalY(latitude: Double, zoom: Float): Double {
        val lat = latitude.coerceIn(MIN_LAT, MAX_LAT)

        val sinLat = sin(lat * PI / 180)
        val y = 0.5 - log2((1 + sinLat) / (1 - sinLat)) / (4 * PI)

        val mapSize = mapSize(zoom)
        return (y * mapSize + 0.5).coerceIn(0.0, mapSize - 1)
    }

    // Map width and height in pixels
    private fun mapSize(zoom: Float): Double {
        return tileSize * zoom.toDouble().pow(2)
    }

    // The resolution of the map at the ground level (calculated with meters/pixel)
    private fun resolution(latLng: LatLng, zoom: Float): Double {
        return cos(latLng.latitude * PI / 180) * EARTH_CIRCUMFERENCE / mapSize(zoom)
    }

}
