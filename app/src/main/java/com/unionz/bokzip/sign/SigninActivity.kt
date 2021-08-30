package com.unionz.bokzip.sign

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.unionz.bokzip.R
import com.unionz.bokzip.home.MainActivity
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
    }

    fun onClick(v : View) {
        when(v){
            kakao -> { // @TODO : 카카오, 구글 로그인 구현하기 (현재 : 임시로 카테고리 설정 화면으로 이동)
                var intent = Intent(this, SignupCategorySetActivity::class.java)
                startActivity(intent)
            }
            google -> {

            }
            browsing -> { // 로그인 전 둘러보기 버튼
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}