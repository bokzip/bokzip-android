package com.unionz.bokzip.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)

    // 가입시 사용자가 설정한 맞춤 정보 중 관심 분야 정보
    fun getCategory(defCategroy: String): String {
        return prefs.getString("category", defCategroy).toString()
    }

    fun setCategory(str: String) {
        prefs.edit().putString("category", str).apply()
    }
}