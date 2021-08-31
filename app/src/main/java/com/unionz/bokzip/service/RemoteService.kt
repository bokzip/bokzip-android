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
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    // 추천 탭에서 중앙부처의 특정 분야에 해당하는 복지리스트 가져오기
    @GET("/post/center/category/{category}")
    fun getRecommendBokji(@Path("category") category: String): Call<ArrayList<RecommendBokjiItem>>

    // 추천 탭에서 중앙부처 복지 클릭 시 복지 상세 정보 가져오기
    @GET("/post/center/{id}")
    fun getDetailRecommendBokji(@Path("id") id: String): Call<RecommendBokjiContent>

    // 일반 탭 복지 리스트 가져오기
    @GET("/post/generals")
    fun getGeneralBokji(): Call<ArrayList<GeneralBokjiItem>>

    // 일반 탭 복지 클릭 시 복지 상세정보 가져오기
    @GET("/post/general/{id}")
    fun getDetailGeneralBokji(@Path("id") id: String): Call<GeneralBokjiContent>

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