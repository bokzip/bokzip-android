package com.unionz.bokzip.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.unionz.bokzip.FilterBottomSheet
import com.unionz.bokzip.R
import com.unionz.bokzip.adapter.BokjiItemAdapter
import com.unionz.bokzip.model.BokjiItem
import com.unionz.bokzip.util.StrUtil
import kotlinx.android.synthetic.main.fragment_tap_recommend.*

class RecommendFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_tap_recommend, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var strUtil = StrUtil()

        // @deprecated : 테스트용 데이터
        var bokziList = arrayListOf<BokjiItem>(
            BokjiItem(0, strUtil.setNewLine("어촌생활 돌봄지원"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", false),
            BokjiItem(1, strUtil.setNewLine("산재근로자 케어센터지원"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", false),
            BokjiItem(2, strUtil.setNewLine("건강보험료 할인"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", true),

            BokjiItem(3, strUtil.setNewLine("이동통신요금감면"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", true),
            BokjiItem(4, strUtil.setNewLine("교육복지 우선지원사업"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", true),
            BokjiItem(5, strUtil.setNewLine("청소년방과후아카데미운영지원"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", false),

            BokjiItem(6, strUtil.setNewLine("공공기관 편의시설 설치사업"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", false),
            BokjiItem(7, strUtil.setNewLine("사랑나누리 운영"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", true),
            BokjiItem(8, strUtil.setNewLine("통합사례관리가구 주거환경개선사업"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", true),

            BokjiItem(9, strUtil.setNewLine("공공하수도 사용료 지원"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", false),
            BokjiItem(10, strUtil.setNewLine("장애인연금"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", false),
            BokjiItem(11, strUtil.setNewLine("방과후 보육료 지원"), "https://images.unsplash.com/photo-1532330384785-f94c84352e91?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80", true)
        )

        val adapter = BokjiItemAdapter(requireContext(), bokziList, 1)
        recyclerview.adapter = adapter

        // 2개의 열을 갖는 GridLayout
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerview.layoutManager = gridLayoutManager

        val category = MainActivity.prefs.getCategory("None")
        if(category != "None"){
            category_textview.setText(category)
        }else{
            category_textview.setText("중앙부처 복지")
        }

        btn_filter.setOnClickListener {
            val bottomSheet = FilterBottomSheet()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag);
        }

    }
}