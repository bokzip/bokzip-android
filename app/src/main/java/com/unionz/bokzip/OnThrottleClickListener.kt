package com.unionz.bokzip

import android.util.Log
import android.view.View

// @see 중복 클릭 방지 : https://blog.yena.io/studynote/2019/12/26/Android-Kotlin-ClickListener.html
class OnThrottleClickListener(private val clickListener: View.OnClickListener, private val interval: Long = 300) :
    View.OnClickListener {
    private var clickable = true // @param : 중복 클릭 방지 플래그
    override fun onClick(v: View?) {
        if (clickable) {
            clickable = false
            v?.run {
                postDelayed({
                    clickable = true
                }, interval)
                clickListener.onClick(v)
            }
        } else {
            Log.d("중복 클릭 방지", "waiting for a while")
        }
    }
}