package com.unionz.bokzip.sign

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.unionz.bokzip.R
import com.unionz.bokzip.util.prefs
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
                    categoryResult = getString(R.string.category_education)
                }
                employment.id -> {
                    if (employment.isChecked)
                        category_radio_group_two.clearCheck()
                    categoryResult = getString(R.string.general_category_work)
                }
                health.id -> {
                    if (health.isChecked)
                        category_radio_group_two.clearCheck()
                    categoryResult = getString(R.string.category_health)
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
                    categoryResult = getString(R.string.category_life)
                }
                all.id -> {
                    if (all.isChecked)
                        category_radio_group_one.clearCheck()
                    categoryResult = getString(R.string.category_short_all)
                }
            }

            // 버튼활성화
            next.isEnabled = true
            next.setBackgroundResource(R.drawable.btn_default_bg)
        }
    }

    fun onClick(v: View) {
        when (v) {
            apply -> onBackPressed()
            next -> {
                prefs?.setCategory(categoryResult)
                var intent = Intent(this, SignupLocationSetActivity::class.java)
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
        }
    }
}