package com.unionz.bokzip

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.unionz.bokzip.databinding.ActivityMyBinding
import com.unionz.bokzip.sign.SigninActivity
import com.unionz.bokzip.util.prefs

class MyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my)

        setListener()
    }

    private fun setListener() {
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.logout.setOnClickListener {
            prefs?.resetCookie()
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }
    }
}