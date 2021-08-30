package com.unionz.bokzip.sign

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.unionz.bokzip.IntroActivity
import com.unionz.bokzip.R
import com.unionz.bokzip.home.MainActivity
import kotlinx.android.synthetic.main.activity_signup_location_set.*

class SignupLocationSetActivity : AppCompatActivity() {
    private var isSkipped: Boolean = true // 건너뛰기 여부

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_location_set)
        init()
    }

    private fun init(){
        search_edit_text.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    if(p0.length > 0){ // @TODO : 추후 거주지 검색 구현 완료 시 수정 예정 (현재 : 입력된 텍스트가 존재할 경우 -> 완료 버튼으로 디스플레이)
                        isSkipped = false
                        complete.setBackgroundResource(R.drawable.btn_default_bg)
                        complete.setText("완료")
                    } else{ // 텍스트가 존재하지 않는 경우, 건너뛰기 버튼으로 디스플레이
                        isSkipped = true
                        complete.setBackgroundResource(R.drawable.btn_default_unact_bg)
                        complete.setText("건너뛰기")
                    }
                }
            }
        })

    }

    fun onClick(v: View) {
        when (v) {
            back -> onBackPressed() // 뒤로가기 버튼
            complete -> { // 로그인 완료 버튼
                if(!isSkipped) // 거주지 입력됨 -> 완료 버튼(건너뛰기 x)
                    IntroActivity.prefs.setLocation(search_edit_text.text.toString()) // 사용자가 설정한 거주지 정보 저장
                Toast.makeText(this, "회원가입을 완료했습니다.", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP) // 백스택에 쌓여있는 모든 액티비티 종료
                startActivity(intent)
                finish()
            }
            delete -> { // 검색어 삭제
                search_edit_text.setText("")
            }
//            cur_location->{ // 현재위치 찾기 버튼 : 추후 구현 예정
//
//            }
        }
    }
}