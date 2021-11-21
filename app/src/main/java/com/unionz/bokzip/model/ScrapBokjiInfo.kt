package com.unionz.bokzip.model

data class ScrapBokjiInfo(
    val id: Int,
    val general: RecommendBokjiItem,
    val post: RecommendBokjiItem,
    val userId: String
)