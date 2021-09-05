package com.unionz.bokzip

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unionz.bokzip.model.RecommendBokjiContent
import com.unionz.bokzip.service.RemoteLib
import com.unionz.bokzip.service.RemoteService
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_error.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity: AppCompatActivity(){
    private val TAG = "복지정보 상세 조회"
    private val api = RemoteService.create()
    lateinit var id:String // 복지 정보의 id

//    private var _isUpdated = false
//    override fun updateSet(isUpdated: Boolean) {
//        _isUpdated = isUpdated
//    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("itemId")?:"null"

        if (id != "null"){ // id값이 존재하는 경우
            setContentView(R.layout.activity_detail)
            getViewCount(id)
            getBokjiContent(id)
        } else{ // 시스템 상 오류로 id값을 받아오지 못한 경우, 에러페이지 띄우기
            setContentView(R.layout.activity_error)
            back_error.setOnClickListener{ // 뒤로가기 버튼 클릭
                onBackPressed()
            }
        }
    }

    // 각종 뷰 초기화
    fun init(item: RecommendBokjiContent){
        var isClicked = false
        bokji_title.text = item.title

        // 이미지 적용
        Glide.with(this).load(item.thumbnail).into(thumbnail)

        // 스크랩 여부에 따른 아이콘 설정
        if(item.isScrap == "true"){
            scrap.setImageDrawable(this.getDrawable(R.drawable.ic_scrap))
            isClicked = true
        }

        // 스크랩 클릭 이벤트 처리
        scrap.setOnClickListener { view ->
            if (isClicked) {
                isClicked = false
                RemoteLib(TAG).removeCenterScrap(item.id)
                scrap.setImageDrawable(this.getDrawable(R.drawable.ic_unscrap))
                Toast.makeText(this, "스크랩 해제되었습니다.", Toast.LENGTH_SHORT).show()
                scrap_cnt.text = (Integer.parseInt(scrap_cnt.text.toString())-1).toString()
            } else {
                isClicked = true
                RemoteLib(TAG).saveCenterScrap(item.id)
                scrap.setImageDrawable(this.getDrawable(R.drawable.ic_scrap))
                Toast.makeText(this, "스크랩 되었습니다.", Toast.LENGTH_SHORT).show()
                scrap_cnt.text = (Integer.parseInt(scrap_cnt.text.toString())+1).toString()
            }
            IntroActivity.prefs.setIsUpdate(true)
        }

        // 각 TextView의 text값 설정
        category.text = item.category
        target_text.text = item.target
        criterias_text.text = item.criteria
        description_text.text = item.description
        how_to_apply_text.text = item.howToApply
        contact_text.text = item.contact
        scrap_cnt.text = item.starCount

        // 복지 신청 클릭 시, 해당 복지 사이트로 이동
        apply_url_btn.setOnClickListener { view ->
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.applyUrl))
            startActivity(intent)
        }

        back.setOnClickListener{
            onBackPressed()
        }
    }

    // id값에 해당하는 복지 정보 가져오기
    private fun getBokjiContent(id: String){
        api.getDetailRecommendBokji(id).enqueue(object : Callback<RecommendBokjiContent> {
            override fun onResponse(
                call: Call<RecommendBokjiContent>,
                response: Response<RecommendBokjiContent>
            ) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if (!response.body().toString().isEmpty()) {
                    init(response.body()!!)
                } else {
                    Log.i(TAG, "요청 받은 리소스를 찾을 수 없습니다.")
                }
            }

            override fun onFailure(call: Call<RecommendBokjiContent>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG, "서버 연결에 실패했습니다.")
            }
        })
    }

    // 조회수 증가
    private fun getViewCount(id: String){
        api.getCenterViewCount(id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if (!response.body().toString().isEmpty()) {
                    Log.i(TAG, "조회 수 증가 성공")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG, "서버 연결에 실패했습니다.")
            }
        })
    }
}