package com.unionz.bokzip.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.unionz.bokzip.R
import com.unionz.bokzip.adapter.BokjiItemAdapter
import com.unionz.bokzip.model.BokjiItem
import com.unionz.bokzip.util.StrUtil
import kotlinx.android.synthetic.main.fragment_tap_scrap.*

class ScrapFragment: Fragment() {
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_tap_scrap, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var strUtil = StrUtil()

        // @deprecated : 테스트용 데이터
        var bokziList = arrayListOf<BokjiItem>(
            BokjiItem(0, strUtil.setNewLine("어촌생활 돌봄지원"), "", false),
            BokjiItem(1, strUtil.setNewLine("산재근로자 케어센터지원"), "", false),
            BokjiItem(2, strUtil.setNewLine("건강보험료 할인"), "", true),

            BokjiItem(3, strUtil.setNewLine("이동통신요금감면"), "", true),
            BokjiItem(4, strUtil.setNewLine("교육복지 우선지원사업"), "", true),
            BokjiItem(5, strUtil.setNewLine("청소년방과후아카데미운영지원"), "", false),

            BokjiItem(6, strUtil.setNewLine("공공기관 편의시설 설치사업"), "", false),
            BokjiItem(7, strUtil.setNewLine("사랑나누리 운영"), "", true),
            BokjiItem(8, strUtil.setNewLine("통합사례관리가구 주거환경개선사업"), "", true),

            BokjiItem(9, strUtil.setNewLine("공공하수도 사용료 지원"), "", false),
            BokjiItem(10, strUtil.setNewLine("장애인연금"), "", false),
            BokjiItem(11, strUtil.setNewLine("방과후 보육료 지원"), "", true)
        )


        val adapter = BokjiItemAdapter(requireContext(), bokziList, 1)
        recyclerview.adapter = adapter

        // 2개의 열을 갖는 GridLayout
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerview.layoutManager = gridLayoutManager
        count.setText("10")
    }
}