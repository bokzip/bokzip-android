package com.unionz.bokzip.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)

    }

    }

    // 가입시 사용자가 설정한 맞춤 정보 중 관심 분야 정보
    fun getCategory(): String {
        return prefs.getString("category", "지원").toString()
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

    fun getCookie(): String {
        return prefs.getString("cookie", null).toString()
    }

    fun setCookie(cookie: String) {
        prefs.edit().putString("cookie", cookie).apply()
    }
}