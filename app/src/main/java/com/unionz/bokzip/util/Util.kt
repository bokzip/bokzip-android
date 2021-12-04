package com.unionz.bokzip.util

import android.view.View
import android.webkit.CookieManager
import com.unionz.bokzip.OnThrottleClickListener

var cookieManager: CookieManager = CookieManager.getInstance()
var prefs: PreferenceUtil? = null

/** 중복 클릭 방지 */
fun View.onThrottleClick(action: (v: View) -> Unit) {
    val listener = View.OnClickListener { action(it) }
    setOnClickListener(OnThrottleClickListener(listener))
}