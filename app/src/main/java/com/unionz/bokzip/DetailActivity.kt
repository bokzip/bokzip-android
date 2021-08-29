package com.unionz.bokzip

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unionz.bokzip.model.BokjiContentItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    // 각종 뷰 초기화
    fun init(item : BokjiContentItem){
        var isClicked = false
        bokji_title.text = item.title

        // 이미지 적용
        Glide.with(this).load(item.thumbnail).into(thumbnail)
        thumbnail.background = this.resources.getDrawable(R.drawable.rounding_img, null)
        thumbnail.clipToOutline = true

        // 스크랩 여부에 따른 아이콘 설정
        if(!item.scrap){
            scrap.setImageDrawable(this.getDrawable(R.drawable.ic_scrap))
            isClicked = true
        }

        // 스크랩 클릭 이벤트 처리
        scrap.setOnClickListener { view ->
            if (isClicked) {
                isClicked = false
                scrap.setImageDrawable(this.getDrawable(R.drawable.ic_unscrap))
                Toast.makeText(this, "스크랩 해제되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                isClicked = true
                scrap.setImageDrawable(this.getDrawable(R.drawable.ic_scrap))
                Toast.makeText(this, "스크랩 되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 각 TextView에 text값 설정
        category.text = item.category
        contact.text = item.contact
        criteria.text = item.criteria
        description.text = item.description
        target.text = item.target
        scrap_cnt.text = item.starCount.toString()
    }

    fun onClick(v : View) {
        when(v){
            back -> onBackPressed() // 뒤로가기 버튼
            apply_url_btn -> { // 복지 신청 클릭 시, 해당 복지 사이트로 이동
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://bokjiro.go.kr/nwel/bokjiroMain.do"))
                startActivity(intent)
            }
        }
    }
}