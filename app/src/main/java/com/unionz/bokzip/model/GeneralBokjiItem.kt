package com.unionz.bokzip.model

import com.google.gson.annotations.SerializedName

data class GeneralBokjiItem(
    val id:String,
    val category:String,
    val title:String,
    @SerializedName("image")
    val thumbnail:String,
    val isScrap:String
)
