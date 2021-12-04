package com.unionz.bokzip.model

data class RecommendBokjiItem(
    val id: String,
    val title: String,
    val thumbnail: String,
    val category: String,
    var isScrap: Boolean
)
