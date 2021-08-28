package com.unionz.bokzip.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unionz.bokzip.R
import com.unionz.bokzip.adapter.BokjiItemAdapter
import com.unionz.bokzip.model.BokjiItem
import com.unionz.bokzip.util.StrUtil
import kotlinx.android.synthetic.main.fragment_tap_general.*


class GeneralFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_tap_general, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var strUtil = StrUtil()

        // @deprecated : 테스트용 데이터
        var bokzi = arrayListOf<BokjiItem>(
            BokjiItem(0, strUtil.setNewLine("저소득장애인 생활안정지원 사업"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", false),
            BokjiItem(1, strUtil.setNewLine("산재근로자 케어센터지원"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", false),
            BokjiItem(2, strUtil.setNewLine("건강보험료 할인"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", true),
            BokjiItem(3, strUtil.setNewLine("이동통신요금감면"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", true),
            BokjiItem(4, strUtil.setNewLine("교육복지 우선지원사업"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", true),
            BokjiItem(5, strUtil.setNewLine("청소년방과후아카데미운영지원"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", false)
        )

        // 7가지 카테고리별로 어뎁터를 설정
        val adapter = BokjiItemAdapter(requireContext(), bokzi,2)
        setAdapter(health_recyclerview, adapter)
        setAdapter(culture_recyclerview, adapter)
        setAdapter(transportation_recyclerview, adapter)
        setAdapter(it_recyclerview, adapter)
        setAdapter(care_recyclerview, adapter)
        setAdapter(work_recyclerview, adapter)
        setAdapter(etc_recyclerview, adapter)
    }

    fun setAdapter(recyclerView: RecyclerView, adapter: BokjiItemAdapter){
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }
}