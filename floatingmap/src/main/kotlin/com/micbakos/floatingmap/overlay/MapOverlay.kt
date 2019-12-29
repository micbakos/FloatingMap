package com.micbakos.floatingmap.overlay

import android.content.Context
import android.view.View
import android.widget.FrameLayout

internal class MapOverlay(context: Context) : FrameLayout(context) {

    private val layeredViews = LinkedHashMap<LayerType, ArrayList<View>>()

    fun addElementView(layerType: LayerType, view: View) {
        // Get the list that holds the views of this type
        var viewsList = layeredViews[layerType]

        // Create a new list if there are no views of this type
        if (viewsList == null) {
            viewsList = ArrayList()
            layeredViews[layerType] = viewsList
        }

        // Count the views that are below the view that we are going to add
        var index = 0
        for ((layer, list) in layeredViews) {
            if (layer.ordinal <= layerType.ordinal) {
                index += list.size
            }
        }

        // Add the view to the corresponding list and inside the map overlay
        viewsList.add(view)
        addView(view, index)
    }

    fun removeElementView(layerType: LayerType, view: View) {
        val list = layeredViews[layerType] ?: return

        // Remove the view from the layer's list and the map overlay
        list.remove(view)
        removeView(view)
    }

}
