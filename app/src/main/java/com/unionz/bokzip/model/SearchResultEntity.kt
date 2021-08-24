package com.unionz.bokzip.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// 검색 결과 데이터 구조
@Parcelize
data class SearchResultEntity(
    val fullAddress: String,
    val name: String
) : Parcelable