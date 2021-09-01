package com.unionz.bokzip

import com.unionz.bokzip.model.SearchPoiInfo

class TMapSearchInfo {
    private lateinit var searchPoiInfo : SearchPoiInfo

    public fun getSearchPoiInfo() : SearchPoiInfo {
        return searchPoiInfo
    }

    public fun setSearchPoiInfo(searchPoiInfo: SearchPoiInfo){
        this.searchPoiInfo = searchPoiInfo
    }
}