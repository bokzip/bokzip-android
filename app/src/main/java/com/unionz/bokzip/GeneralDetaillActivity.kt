package com.unionz.bokzip

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unionz.bokzip.model.GeneralBokjiContent
import com.unionz.bokzip.service.RemoteService
import kotlinx.android.synthetic.main.activity_detail_general.*
import kotlinx.android.synthetic.main.activity_error.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GeneralDetaillActivity : AppCompatActivity(){ // @TODO : 다이얼로그로 변경예정
    private val TAG = "복지정보 상세 조회"
    val api = RemoteService.create()
    lateinit var id:String // 복지 정보의 id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getStringExtra("itemId")?:"null"

        if (id != "null"){ // id값이 존재하는 경우
            setContentView(R.layout.activity_detail_general)
            getBokjiContent(id)
        } else{ // 시스템 상 오류로 id값을 받아오지 못한 경우, 에러페이지 띄우기
            setContentView(R.layout.activity_error)
            back_error.setOnClickListener{
                onBackPressed()
            }
        }
    }

    // 각종 뷰 초기화
    fun init(item : GeneralBokjiContent){
        var isClicked = false
        bokji_title.text = item.title

        // 이미지 적용
        Glide.with(this).load(item.image).into(thumbnail)

        // 스크랩 여부에 따른 아이콘 설정
        if(item.isScrap == "true"){
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

        // 각 TextView의 text값 설정
        category.text = item.category
        description_text.text = item.description
        scrap_cnt.text = item.starCount

        back.setOnClickListener{
            onBackPressed()
        }
    }

    // id값에 해당하는 복지 정보 가져오기
    private fun getBokjiContent(id:String){
        api.getDetailGeneralBokji(id).enqueue(object : Callback<GeneralBokjiContent> {
            override fun onResponse(call: Call<GeneralBokjiContent>, response: Response<GeneralBokjiContent>) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if(!response.body().toString().isEmpty()) {
                    init(response.body()!!)
                } else{
                    Log.i(TAG,"요청 받은 리소스를 찾을 수 없습니다.")
                }
            }

            override fun onFailure(call: Call<GeneralBokjiContent>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG,"서버 연결에 실패했습니다.")
            }
        })
    }
}