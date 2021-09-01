package com.unionz.bokzip.model

class LocationItem {
    private var title : String
    private var subTitle : String

    constructor(title:String, subTitle: String) {
        this.title = title
        this.subTitle = subTitle
    }

    fun getTitle() : String {
        return title
    }

    fun setTitle(title: String){
        this.title = title
    }

    fun getSubTitle():String {
        return subTitle
    }

    fun setSubTitle(subTitle: String){
        this.subTitle=subTitle
    }
}