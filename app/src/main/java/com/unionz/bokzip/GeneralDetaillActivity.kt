package com.unionz.bokzip

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unionz.bokzip.model.GeneralBokjiContent
import com.unionz.bokzip.service.RemoteService
import com.unionz.bokzip.viewmodel.FilterViewModel
import kotlinx.android.synthetic.main.activity_detail_general.*
import kotlinx.android.synthetic.main.activity_error.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GeneralDetaillActivity : AppCompatActivity() { // @TODO : 다이얼로그로 변경예정
    private val TAG = "복지정보 상세 조회"
    private val api = RemoteService.create()
    lateinit var id: String
    private val viewModel: FilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getStringExtra(ITEM_ID) ?: "null"

        if (id != "null") {
            setContentView(R.layout.activity_detail_general)
            getViewCount(id)
            getBokjiContent(id)
        } else {
            setContentView(R.layout.activity_error)
            back_error.setOnClickListener {
                onBackPressed()
            }
        }
    }

    // 각종 뷰 초기화
    fun init(item: GeneralBokjiContent) {
        var isClicked = false
        bokji_title.text = item.title

        // 이미지 적용
        Glide.with(this).load(item.thumbnail).into(thumbnail)

        // 스크랩 여부에 따른 아이콘 설정
        if (item.isScrap == "true") {
            scrap.setImageDrawable(this.getDrawable(R.drawable.ic_scrap))
            isClicked = true
        }

        // 스크랩 클릭 이벤트 처리
        scrap.setOnClickListener { view ->
            if (isClicked) {
                viewModel.removeGeneralScrap(id)
                isClicked = false
                scrap.setImageDrawable(getDrawable(R.drawable.ic_unscrap))
                Toast.makeText(this@GeneralDetaillActivity, "스크랩 해제되었습니다.", Toast.LENGTH_SHORT)
                    .show()
                scrap_cnt.text = (Integer.parseInt(scrap_cnt.text.toString()) - 1).toString()

            } else {
                viewModel.saveGeneralScrap(id)
                isClicked = true
                scrap.setImageDrawable(getDrawable(R.drawable.ic_scrap))
                Toast.makeText(this@GeneralDetaillActivity, "스크랩 되었습니다.", Toast.LENGTH_SHORT)
                    .show()
                scrap_cnt.text = (Integer.parseInt(scrap_cnt.text.toString()) + 1).toString()
            }
        }

        category.text = item.category
        description_text.text = item.description
        scrap_cnt.text = item.starCount

        apply.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getBokjiContent(id: String) {
        api.getDetailGeneralBokji(id).enqueue(object : Callback<GeneralBokjiContent> {
            override fun onResponse(
                call: Call<GeneralBokjiContent>,
                response: Response<GeneralBokjiContent>
            ) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if (!response.body().toString().isEmpty()) {
                    init(response.body()!!)
                } else {
                    Log.i(TAG, "요청 받은 리소스를 찾을 수 없습니다.")
                }
            }

            override fun onFailure(call: Call<GeneralBokjiContent>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG, "서버 연결에 실패했습니다.")
            }
        })
    }

    private fun getViewCount(id: String) {
        api.getGeneralViewCount(id).enqueue(object : Callback<ResponseBody> {
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

    companion object {
        private const val ITEM_ID = "itemId"
    }
}