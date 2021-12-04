package com.unionz.bokzip

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.unionz.bokzip.databinding.ActivityDetailBinding
import com.unionz.bokzip.model.RecommendBokjiContent
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.service.RemoteService
import com.unionz.bokzip.util.prefs
import com.unionz.bokzip.viewmodel.FilterViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_error.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private val TAG = "복지정보 상세 조회"
    private val api = RemoteService.create()
    lateinit var id: String // 복지 정보의 id
    private var position: Int? = null
    private val viewModel: FilterViewModel by viewModels()
    private lateinit var item: RecommendBokjiItem
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        id = intent.getStringExtra(ITEM_ID) ?: "null"
        position = intent.getIntExtra(ITEM_POSITION, -1)

        prefs?.setScrapItemInfo(position!!, null)
        if (id != "null") { // id값이 존재하는 경우
            setContentView(R.layout.activity_detail)
            getViewCount(id)
            getBokjiContent(id)
        } else { // 시스템 상 오류로 id값을 받아오지 못한 경우, 에러페이지 띄우기
            setContentView(R.layout.activity_error)
            back_error.setOnClickListener { // 뒤로가기 버튼 클릭
                onBackPressed()
            }
        }
    }

    private fun init(content: RecommendBokjiContent) {
        item = RecommendBokjiItem(
            content.id,
            content.title,
            content.thumbnail,
            content.category,
            content.isScrap
        )
        var isClicked = false
        bokji_title.text = content.title
        Glide.with(this).load(content.thumbnail).into(thumbnail)
        if (content.isScrap) {
            scrap.setImageDrawable(getDrawable(R.drawable.ic_scrap))
            isClicked = true
        }

        scrap.setOnClickListener { view ->
            item.isScrap = !item.isScrap
            prefs?.setScrapItemInfo(position!!, item.isScrap.toString())

            if (isClicked) {
                viewModel.removeCenterScrap(content.id)
                isClicked = false
                scrap.setImageDrawable(getDrawable(R.drawable.ic_unscrap))
                Toast.makeText(this, "스크랩 해제되었습니다.", Toast.LENGTH_SHORT).show()
                scrap_cnt.text = (Integer.parseInt(scrap_cnt.text.toString()) - 1).toString()

            } else {
                viewModel.saveCenterScrap(content.id)
                isClicked = true
                scrap.setImageDrawable(getDrawable(R.drawable.ic_scrap))
                Toast.makeText(this, "스크랩 되었습니다.", Toast.LENGTH_SHORT).show()
                scrap_cnt.text =
                    (Integer.parseInt(scrap_cnt.text.toString()) + 1).toString()
            }
        }

        category.text = content.category
        target_text.text = content.target
        criterias_text.text = content.criteria
        description_text.text = content.description
        how_to_apply_text.text = content.howToApply
        contact_text.text = content.contact
        scrap_cnt.text = content.starCount

        // 복지 신청 클릭 시, 해당 복지 사이트로 이동
        apply_url_btn.setOnClickListener { view ->
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(content.applyUrl))
            startActivity(intent)
        }

        apply.setOnClickListener {
            onBackPressed()
        }
    }

    // id값에 해당하는 복지 정보 가져오기
    private fun getBokjiContent(id: String) {
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
    private fun getViewCount(id: String) {
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

    companion object {
        private const val ITEM_ID = "itemId"
        private const val ITEM_POSITION = "itemPosition"
    }
}