package com.unionz.bokzip.model

data class BokjiContentItem(
    val id:Int,
    val title:String,
    val thumbnail:String,
    val scrap:Boolean,
    val category:String,
    val contact:String,
    val criteria:String,
    val description:String,
    val target:String,
    val starCount:Int
)
