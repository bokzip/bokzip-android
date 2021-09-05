package com.unionz.bokzip.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.unionz.bokzip.FilterBottomSheet
import com.unionz.bokzip.IntroActivity
import com.unionz.bokzip.R
import com.unionz.bokzip.adapter.RecommendBokjiItemAdapter
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.service.RemoteService
import com.unionz.bokzip.util.PreferenceUtil
import kotlinx.android.synthetic.main.fragment_tap_recommend.*
import kotlinx.android.synthetic.main.fragment_tap_recommend.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecommendFragment: Fragment() {
    private val TAG = "추천 탭"
    private val api = RemoteService.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_tap_recommend, container, false)
        getBokjiItem() // 추천 복지 리스트 가져오기

        view.btn_filter.setOnClickListener {
            val bottomSheet = FilterBottomSheet()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag);
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        // DetailActivity에서 변경사항이 존재하는 경우 아이템 갱신, 추후 코드 수정할 예정
        var pref : PreferenceUtil = IntroActivity.prefs
        if (pref.getIsUpdate()) {
            getBokjiItem()
            pref.setIsUpdate(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBokjiItem()
    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//    }

    fun init(bokziList: ArrayList<RecommendBokjiItem>){
        val adapter = RecommendBokjiItemAdapter(requireContext(), bokziList)
        recyclerview.adapter = adapter

        // 2개의 열을 갖는 GridLayout
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerview.layoutManager = gridLayoutManager

        // 사용자가 설정한 관심 분야가 존재할 경우 상단 타이틀에 분야정보를 디스플레이
        val category = IntroActivity.prefs.getCategory()
        if(category != "전체"){
            category_textview.setText(category)
        }else{
            category_textview.setText("중앙부처 복지")
        }
    }

    // 사용자 맞춤정보에 해당하는 복지 리스트 가져오기
    private fun getBokjiItem(){
        val category = IntroActivity.prefs.getCategory() // 사용자의 관심 분야에 해당하는 복지를 요청
        api.getRecommendBokji(category).enqueue(object : Callback<ArrayList<RecommendBokjiItem>> {
            override fun onResponse(
                call: Call<ArrayList<RecommendBokjiItem>>,
                response: Response<ArrayList<RecommendBokjiItem>>
            ) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if (!response.body().toString().isEmpty()) {
                    init(response.body()!!)
                } else {
                    Log.i(TAG, "요청 받은 리소스를 찾을 수 없습니다.")

                }
            }

            override fun onFailure(call: Call<ArrayList<RecommendBokjiItem>>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG, "서버 연결에 실패했습니다.")
            }
        })
    }
}