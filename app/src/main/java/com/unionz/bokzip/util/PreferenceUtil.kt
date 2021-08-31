package com.unionz.bokzip.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)

    // 소셜로그인 인증 토큰
    fun getToken(): String {
        return prefs.getString("token", "").toString()
    }

    fun setToken(token: String) {
        prefs.edit().putString("category", token).apply()
    }

    // 가입시 사용자가 설정한 맞춤 정보 중 관심 분야 정보
    fun getCategory(): String {
        return prefs.getString("category", "전체").toString()
    }

    fun setCategory(category: String) {
        prefs.edit().putString("category", category).apply()
    }

    // 가입시 사용자가 설정한 맞춤 정보 중 거주지 정보
    fun getLocation(): String {
        return prefs.getString("location", "").toString() // 추후 default value값 변경 예정
    }

    fun setLocation(location: String) {
        prefs.edit().putString("location", location).apply()
    }
}