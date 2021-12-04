package com.unionz.bokzip

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.unionz.bokzip.home.MainActivity
import com.unionz.bokzip.sign.SigninActivity
import com.unionz.bokzip.util.PreferenceUtil
import com.unionz.bokzip.util.prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        prefs = PreferenceUtil(applicationContext)

        initializeView()
    }

    private fun initializeView() {
        lateinit var intent: Intent
        val cookie = prefs?.getCookie()
        intent = if (cookie.isNullOrBlank()) {
            Intent(this, SigninActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }

        lifecycleScope.launch(Dispatchers.Main) {
            job = launch {
                delay(SPLASH_VIEW_TIME)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onPause() {
        job?.cancel()
        super.onPause()
    }

    companion object {
        private const val SPLASH_VIEW_TIME: Long = 2000
    }
}