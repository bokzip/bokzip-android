package com.unionz.bokzip.service

import com.unionz.bokzip.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RemoteService {

    // 추천 탭
    @GET("/post/centers")
    suspend fun getRecommendBokji(): Response<ArrayList<RecommendBokjiItem>?>

    // 일반 탭
    @GET("/post/generals")
    suspend fun getGeneralBokji(): Response<ArrayList<RecommendBokjiItem>>

    // 스크랩 탭
    @GET("/scraps")
    suspend fun getScrapBokji(@Header("Cookie") cookie: String): Response<ScrapItems>

    @GET("/scraps/centers/{center_id}")
    suspend fun saveCenterScrap(@Header("Cookie") cookie: String, @Path("center_id") center_id: String): Response<ScrapItem>

    @DELETE("/scraps/centers/{center_id}")
    suspend fun removeCenterScrap(@Header("Cookie") cookie: String, @Path("center_id") center_id: String): Response<ScrapItem>

    @GET("/scraps/generals/{generals_id}")
    suspend fun saveGeneralScrap(@Header("Cookie") cookie: String, @Path("generals_id") generals_id: String): Response<ScrapItem>

    @DELETE("/scraps/generals/{generals_id}")
    suspend fun removeGeneralScrap(@Header("Cookie") cookie: String, @Path("generals_id") generals_id: String): Response<ScrapItem>

    // 필터
    @GET("/post/center/custom")
    suspend fun getCategoryFilterResult(
        @Query("category") category: String?,
        @Query("area") area: String?,
        @Query("sort") sort: String?
    ): Response<ArrayList<RecommendBokjiItem>?>

    // 상세정보
    @GET("/post/center/{id}")
    fun getDetailRecommendBokji(@Path("id") id: String): Call<RecommendBokjiContent>

    @GET("/post/general/{id}")
    fun getDetailGeneralBokji(@Path("id") id: String): Call<GeneralBokjiContent>

    // 조회수
    @GET("/post/center/view/{id}")
    fun getCenterViewCount(@Path("id") id: String): Call<ResponseBody>

    @GET("/post/general/view/{id}")
    fun getGeneralViewCount(@Path("id") id: String): Call<ResponseBody>

    companion object {
        private const val BASE_URL = "https://bokzip2.herokuapp.com/"

        fun create(): RemoteService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteService::class.java)
        }
    }
}
