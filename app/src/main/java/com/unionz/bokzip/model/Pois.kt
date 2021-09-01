package com.unionz.bokzip.model

class Pois{
    private var poi : ArrayList<Poi>? = null

    fun getPoi() : ArrayList<Poi>? {
        return poi
    }

    fun setPoi(poi : ArrayList<Poi>){
        this.poi = poi
    }
}
