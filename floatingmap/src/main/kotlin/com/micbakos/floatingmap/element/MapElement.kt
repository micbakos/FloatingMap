package com.micbakos.floatingmap.element

interface MapElement {

    fun initialize()

    fun placeOnMap()

    fun onElementAdded() {}

    fun dispose()

    fun getBounds(): MapElementBounds?

}
