package com.unionz.bokzip

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.unionz.bokzip.home.MainActivity
import com.unionz.bokzip.sign.SigninActivity
import com.unionz.bokzip.util.PreferenceUtil

class IntroActivity : AppCompatActivity() {
    companion object { lateinit var prefs: PreferenceUtil }
    private val SPLASH_VIEW_TIME:Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        prefs = PreferenceUtil(applicationContext)

        loadSplashScreen()
    }

    private fun loadSplashScreen(){
        lateinit var intent: Intent
        val token = prefs.getToken()

        if(token.isNullOrBlank()){ // 로그인 이력이 없는 사용자의 경우, 로그인 화면으로 이동
            Log.i("TEST", "loadSplashScreen: 토큰 없음")
            intent = Intent(this, SigninActivity::class.java)
        }else{ // 간편로그인을 한 사용자의 경우 메인 화면으로 이동
            Log.i("TEST", "loadSplashScreen: 토큰 있음")
            intent = Intent(this, MainActivity::class.java)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, SPLASH_VIEW_TIME)
    }
}