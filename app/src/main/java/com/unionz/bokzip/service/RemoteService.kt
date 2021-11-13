package com.unionz.bokzip.service

import com.unionz.bokzip.model.*
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.net.CookieManager

interface RemoteService {

    // 추천 탭
    @GET("/post/centers")
    suspend fun getRecommendBokji(): Response<ArrayList<RecommendBokjiItem>?>

    // 일반 탭
    @GET("/post/generals")
    fun getGeneralBokji(): Call<ArrayList<GeneralBokjiItem>>

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

    // 스크랩
    @POST("/post/center/{id}")
    fun saveCenterScrap(@Path("id") id: String): Call<RecommendBokjiItem>
    @DELETE("/post/center/{id}")
    fun removeCenterScrap(@Path("id") id: String): Call<Void>
    @POST("/post/general/{id}")
    fun saveGeneralScrap(@Path("id") id: String): Call<GeneralBokjiItem>
    @DELETE("/post/general/{id}")
    fun removeGeneralScrap(@Path("id") id: String): Call<Void>

    // 조회수
    @GET("/post/center/view/{id}")
    fun getCenterViewCount(@Path("id") id: String): Call<ResponseBody>
    @GET("/post/general/view/{id}")
    fun getGeneralViewCount(@Path("id") id: String): Call<ResponseBody>

    // static 처럼 공유객체로 사용가능, 모든 인스턴스가 공유하는 객체로서 동작
    companion object {
        private const val BASE_URL =
            "https://bokzip2.herokuapp.com/" // @TODO : 추후 주소 변경하기 (에뮬 전용 주소) http://10.0.2.2:8080

        fun create(): RemoteService {
            val client = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(CookieManager()))
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteService::class.java)
        }
    }
}