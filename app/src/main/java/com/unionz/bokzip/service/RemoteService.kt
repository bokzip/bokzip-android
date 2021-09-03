package com.unionz.bokzip.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.unionz.bokzip.model.GeneralBokjiContent
import com.unionz.bokzip.model.GeneralBokjiItem
import com.unionz.bokzip.model.RecommendBokjiContent
import com.unionz.bokzip.model.RecommendBokjiItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RemoteService {

    // 추천 탭
    @GET("/post/center/category/{category}")
    fun getRecommendBokji(@Path("category") category: String): Call<ArrayList<RecommendBokjiItem>>
    @GET("/post/generals")
    fun getGeneralBokji(): Call<ArrayList<GeneralBokjiItem>>

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

    // static 처럼 공유객체로 사용가능, 모든 인스턴스가 공유하는 객체로서 동작
    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080" // @TODO : 추후 주소 변경하기 (에뮬 전용 주소)

        fun create(): RemoteService {
            val gson : Gson =   GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RemoteService::class.java)
        }
    }
}