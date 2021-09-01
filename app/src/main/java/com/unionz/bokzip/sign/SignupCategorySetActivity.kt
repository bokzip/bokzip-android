package com.unionz.bokzip.sign

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.unionz.bokzip.IntroActivity
import com.unionz.bokzip.R
import kotlinx.android.synthetic.main.activity_signup_category_set.*

class SignupCategorySetActivity : AppCompatActivity() {
    private var categoryResult: String = "" // 사용자가 선택한 분야

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_category_set)
        init()
    }

    private fun init() {
        // 카테고리 윗줄
        category_radio_group_one.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                education.id -> {
                    if (education.isChecked)
                        category_radio_group_two.clearCheck()
                    categoryResult = "교육지원"
                }
                employment.id -> {
                    if (employment.isChecked)
                        category_radio_group_two.clearCheck()
                    categoryResult = "고용지원"
                }
                health.id -> {
                    if (health.isChecked)
                        category_radio_group_two.clearCheck()
                    categoryResult = "건강지원"
                }
            }

            // 버튼활성화
            next.isEnabled = true
            next.setBackgroundResource(R.drawable.btn_default_bg)
        }

        // 카테고리 아랫줄
        category_radio_group_two.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                life.id -> {
                    if (life.isChecked)
                        category_radio_group_one.clearCheck()
                    categoryResult = "생활지원"
                }
                all.id -> {
                    if (all.isChecked)
                        category_radio_group_one.clearCheck()
                    categoryResult = "전체"
                }
            }

            // 버튼활성화
            next.isEnabled = true
            next.setBackgroundResource(R.drawable.btn_default_bg)
        }
    }

    fun onClick(v: View) {
        when (v) {
            back -> onBackPressed() // 뒤로가기 버튼
            next -> { // 복지 신청 클릭 시, 해당 복지 사이트로 이동
                IntroActivity.prefs.setCategory(categoryResult) // 사용자가 설정한 카테고리 값 저장
                var intent = Intent(this, SignupLocationSetActivity::class.java)
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION) // 화면전환 에니메이션 제거
                startActivity(intent)
            }
        }
    }
}