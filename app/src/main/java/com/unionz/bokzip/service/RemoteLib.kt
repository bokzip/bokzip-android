package com.unionz.bokzip.service

import android.util.Log
import com.unionz.bokzip.model.GeneralBokjiItem
import com.unionz.bokzip.model.RecommendBokjiItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 중앙부처 모듈
class RemoteLib(private val TAG:String){
    private val api = RemoteService.create()

    // 중앙부처 복지 스크랩
    fun saveCenterScrap(id : String){
        api.saveCenterScrap(id).enqueue(object : Callback<RecommendBokjiItem> {
            override fun onResponse(call: Call<RecommendBokjiItem>, response: Response<RecommendBokjiItem>) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if(!response.body().toString().isEmpty()) {
                    Log.i(TAG,"스크랩 성공했습니다.")
                } else{
                    Log.i(TAG,"요청 받은 리소스를 저장할 수 없습니다.")
                }
            }

            override fun onFailure(call: Call<RecommendBokjiItem>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG,"서버 연결에 실패했습니다.")
            }
        })
    }

    fun removeCenterScrap(id: String){
        api.removeCenterScrap(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if(!response.body().toString().isEmpty()) {
                    Log.i(TAG,"스크랩 취소되었습니다.")
                } else{
                    Log.i(TAG,"요청 받은 리소스를 저장할 수 없습니다.")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG,"서버 연결에 실패했습니다.")
            }
        })
    }

    // 일반 복지 스크랩
    fun saveGeneralScrap(id : String){
        api.saveGeneralScrap(id).enqueue(object : Callback<GeneralBokjiItem> {
            override fun onResponse(call: Call<GeneralBokjiItem>, response: Response<GeneralBokjiItem>) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if(!response.body().toString().isEmpty()) {
                    Log.i(TAG,"스크랩 성공했습니다.")
                } else{
                    Log.i(TAG,"요청 받은 리소스를 저장할 수 없습니다.")
                }
            }

            override fun onFailure(call: Call<GeneralBokjiItem>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG,"서버 연결에 실패했습니다.")
            }
        })
    }

    fun removeGeneralScrap(id: String){
        api.removeGeneralScrap(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if(!response.body().toString().isEmpty()) {
                    Log.i(TAG,"스크랩 취소되었습니다.")
                } else{
                    Log.i(TAG,"요청 받은 리소스를 저장할 수 없습니다.")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG,"서버 연결에 실패했습니다.")
            }
        })
    }
}