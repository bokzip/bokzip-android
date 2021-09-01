package com.unionz.bokzip.model

class SearchPoiInfo(
    private var totalCount: String,
    private var count: String,
    private var page: String,
    private var pois: Pois){

    fun getTotalCount() : String {
        return totalCount
    }

    fun setTotalCount(totalCount: String){
        this.totalCount = totalCount
    }

    fun getCount():String{
        return count
    }

    fun setCount(count: String){
        this.count = count
    }

    fun getPage() :String {
        return page
    }

    fun setPage(page: String){
        this.page = page
    }

    fun getPois() :Pois{
        return pois
    }

    fun setPois(pois : Pois){
        this.pois = pois
    }
}
