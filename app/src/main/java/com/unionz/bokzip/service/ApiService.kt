package com.unionz.bokzip.service

import com.unionz.bokzip.model.AddressInfoResponse
import com.unionz.bokzip.model.SearchResponse
import com.unionz.bokzip.util.Key
import com.unionz.bokzip.util.Url
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// 레트로핏에 사용할 Tmap API 인터페이스 정의
interface ApiService {

    companion object {
        const val MAX_PAGE_CONTENT_SIZE = 10
        const val VERSION = 1
        const val START_PAGE = 1
    }

    @GET(Url.GET_TMAP_LOCATION)
    suspend fun getSearchLocation(
        @Header("appKey") appKey: String = Key.TMAP_API,
        @Query("version") version: Int = VERSION,
        @Query("callback") callback: String? = null,
        @Query("page") page: Int = START_PAGE,
        @Query("count") count: Int = MAX_PAGE_CONTENT_SIZE, // 한페이지에 얼마나 나타낼 지
        @Query("searchKeyword") keyword: String, // 검색어
        @Query("areaLLCode") areaLLCode: String? = null,
        @Query("areaLMCode") areaLMCode: String? = null,
        @Query("resCoordType") resCoordType: String? = null,
        @Query("searchType") searchType: String? = null,
        @Query("multiPoint") multiPoint: String? = null,
        @Query("searchtypCd") searchtypCd: String? = null,
        @Query("radius") radius: String? = null,
        @Query("reqCoordType") reqCoordType: String? = null,
        @Query("centerLon") centerLon: String? = null,
        @Query("centerLat") centerLat: String? = null
    ): Response<SearchResponse>

    @GET(Url.GET_TMAP_REVERSE_GEO_CODE)
    suspend fun getReverseGeoCode(
        @Header("appKey") appKey: String = Key.TMAP_API,
        @Query("version") version: Int = VERSION,
        @Query("callback") callback: String? = null,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("coordType") coordType: String? = null,
        @Query("addressType") addressType: String? = null
    ): Response<AddressInfoResponse>
}