package com.unionz.bokzip.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.unionz.bokzip.FilterBottomSheet
import com.unionz.bokzip.R
import com.unionz.bokzip.adapter.RecommendBokjiItemAdapter
import com.unionz.bokzip.model.FilterBokjiItem
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.service.RemoteService
import com.unionz.bokzip.viewmodel.FilterViewModel
import kotlinx.android.synthetic.main.fragment_tap_recommend.*
import kotlinx.android.synthetic.main.fragment_tap_recommend.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendFragment: Fragment() {
    private val TAG = "추천 탭"
    private val api = RemoteService.create()
    private val viewModel: FilterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_tap_recommend, container, false)
        getBokjiItem()

        view.btn_filter.setOnClickListener {
            val bottomSheet = FilterBottomSheet()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag);
        }

        return view
    }

    fun init(bokjiList: ArrayList<RecommendBokjiItem>){
        val adapter = RecommendBokjiItemAdapter(requireContext(), bokjiList)
        recyclerview.adapter = adapter

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerview.layoutManager = gridLayoutManager

        viewModel.getCompleted().observe(viewLifecycleOwner) { isCompleted ->
            val category = viewModel.getFilterCategory()?.value
            val sortOption = viewModel.getSortOption()?.value
            if (isCompleted && (category != null || sortOption != null)) {

                viewModel.reset()
            }
        }
    }

    private fun getBokjiItem(){
        api.getRecommendBokji().enqueue(object : Callback<ArrayList<RecommendBokjiItem>> {
            override fun onResponse(
                call: Call<ArrayList<RecommendBokjiItem>>,
                response: Response<ArrayList<RecommendBokjiItem>>
            ) {
                Log.i(TAG, response.body().toString())
                // 통신 성공
                if (response.body().toString().isNotEmpty()) {
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