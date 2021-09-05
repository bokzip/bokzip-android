package com.unionz.bokzip.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unionz.bokzip.IntroActivity
import com.unionz.bokzip.R
import com.unionz.bokzip.adapter.GeneralBokjiItemAdapter
import com.unionz.bokzip.model.GeneralBokjiItem
import com.unionz.bokzip.service.RemoteService
import com.unionz.bokzip.util.PreferenceUtil
import kotlinx.android.synthetic.main.fragment_tap_general.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GeneralFragment: Fragment() {
    private val TAG = "일반 탭"
    private val api = RemoteService.create()

    // @param : 7가지 카테고리 별 복지 리스트 데이터
    private var health = ArrayList<GeneralBokjiItem>()
    private var culture = ArrayList<GeneralBokjiItem>()
    private var transportation = ArrayList<GeneralBokjiItem>()
    private var it = ArrayList<GeneralBokjiItem>()
    private var care = ArrayList<GeneralBokjiItem>()
    private var work = ArrayList<GeneralBokjiItem>()
    private var etc = ArrayList<GeneralBokjiItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_tap_general, container, false)
        getBokjiItem() // 일반 복지 리스트 가져오기

        return view
    }

    override fun onResume() {
        super.onResume()
        // GeneralDetailActivity에서 변경사항이 존재하는 경우 아이템 갱신, 추후 코드 수정할 예정
        var pref : PreferenceUtil = IntroActivity.prefs
        if (pref.getIsUpdate()) {
            getBokjiItem()
            pref.setIsUpdate(false)
        }
    }

    fun init(bokjiList:ArrayList<GeneralBokjiItem>){

        // 카테고리 값에 대응되는 배열에 복지정를를 저장
        for (i:Int in 0..bokjiList.size-1){
            when(bokjiList[i].category){
              "건강/의료" -> health.add(bokjiList[i])
                "문화"-> culture.add(bokjiList[i])
                "이동"-> transportation.add(bokjiList[i])
                "전화/인터넷/TV"-> it.add(bokjiList[i])
                "보육"-> care.add(bokjiList[i])
                "근로"-> work.add(bokjiList[i])
                "기타"-> etc.add(bokjiList[i])
            }
        }

        // 7가지 카테고리별로 어뎁터를 설정
        setAdapter(health_recyclerview, GeneralBokjiItemAdapter(requireContext(), health))
        setAdapter(culture_recyclerview, GeneralBokjiItemAdapter(requireContext(), culture))
        setAdapter(transportation_recyclerview, GeneralBokjiItemAdapter(requireContext(), transportation))
        setAdapter(it_recyclerview, GeneralBokjiItemAdapter(requireContext(), it))
        setAdapter(care_recyclerview, GeneralBokjiItemAdapter(requireContext(), care))
        setAdapter(work_recyclerview, GeneralBokjiItemAdapter(requireContext(), work))
        setAdapter(etc_recyclerview, GeneralBokjiItemAdapter(requireContext(), etc))
    }

    // 어뎁터 설정
    fun setAdapter(recyclerView: RecyclerView, adapter: GeneralBokjiItemAdapter){
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    // 사용자 맞춤정보에 해당하는 복지 리스트 가져오기
    private fun getBokjiItem(){
        api.getGeneralBokji().enqueue(object : Callback<ArrayList<GeneralBokjiItem>> {
            override fun onResponse(call: Call<ArrayList<GeneralBokjiItem>>, response: Response<ArrayList<GeneralBokjiItem>>) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if(!response.body().toString().isEmpty()) {
                    init(response.body()!!)
                } else{
                    Log.i(TAG,"요청 받은 리소스를 찾을 수 없습니다.")
                }
            }

            override fun onFailure(call: Call<ArrayList<GeneralBokjiItem>>, t: Throwable) {
                // 통신 실패
                Log.i(TAG, t.message.toString())
                Log.i(TAG,"서버 연결에 실패했습니다.")
            }
        })
    }
}