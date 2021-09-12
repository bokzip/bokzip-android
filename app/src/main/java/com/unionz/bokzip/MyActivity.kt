package com.unionz.bokzip

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.back -> onBackPressed()
        }
    }
}