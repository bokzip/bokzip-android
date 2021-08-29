package com.unionz.bokzip.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.unionz.bokzip.R
//import com.unionz.bokzip.adapter.BokjiItemAdapter
import com.unionz.bokzip.model.BokjiItem
import com.unionz.bokzip.FilterBottomSheet

import kotlinx.android.synthetic.main.fragment_tap_recommend.view.*

class RecommendFragment: Fragment() {
    var bokziList = arrayListOf<BokjiItem>(
//        BokjiItem(0, setNewLine("어촌생활 돌봄지원"), "", false),
//        BokjiItem(1, setNewLine("산재근로자 케어센터지원"), "", false),
//        BokjiItem(2, setNewLine("건강보험료 할인"), "", true),
//
//        BokjiItem(3,setNewLine("이동통신요금감면"), "", true),
//        BokjiItem(4, setNewLine("교육복지 우선지원사업"), "", true),
//        BokjiItem(5, setNewLine("청소년방과후아카데미운영지원"), "", false),
//
//        BokjiItem(6,setNewLine("공공기관 편의시설 설치사업"), "", false),
//        BokjiItem(7, setNewLine("사랑나누리 운영"), "", true),
//        BokjiItem(8, setNewLine("통합사례관리가구 주거환경개선사업"), "", true),
//
//        BokjiItem(9, setNewLine("공공하수도 사용료 지원"), "", false),
//        BokjiItem(10, setNewLine("장애인연금"), "", false),
//        BokjiItem(11, setNewLine("방과후 보육료 지원"), "", true)

//        BokjiItem(0, "어촌생활  돌봄지원", "", false),
//        BokjiItem(1, "산재근로자 케어센터지원", "", false),
//        BokjiItem(2, "건강보험료 할인", "", true),
//
//        BokjiItem(3,"이동통신요금감면", "", true),
//        BokjiItem(4, "교육복지 우선지원사업", "", true),
//        BokjiItem(5, "청소년방과후아카데미운영지원", "", false),
//
//        BokjiItem(6,"공공기관 편의시설 설치사업", "", false),
//        BokjiItem(7, "사랑나누리 운영", "", true),
//        BokjiItem(8, "통합사례관리가구 주거환경개선사업", "", true),
//
//        BokjiItem(9, "공공하수도 사용료 지원", "", false),
//        BokjiItem(10, "장애인연금", "", false),
//        BokjiItem(11, "방과후 보육료 지원", "", true)
    )
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view: View = inflater.inflate(R.layout.fragment_tap_recommend, container, false)

//        val adapter = BokjiItemAdapter(requireContext(), bokziList)
//        view.recyclerview.adapter = adapter
//
//        // 2개의 열을 갖는 GridLayout
//        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
//        view.recyclerview.layoutManager = gridLayoutManager
//
//        val category = MainActivity.prefs.getString("category", "None")
//
//        if(category != "None"){
//            view.category_textview.setText(category)
//        }else{
//            view.category_textview.setText("중앙부처 복지")
//        }

        view.btn_filter.setOnClickListener {
            val bottomSheet = FilterBottomSheet()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag);
        }

        return view
    }

    fun setNewLine(str: String) : String{
        val words = str.split(" ")
        var title = ""
        if (words.size == 0) return title
        for(i in 0..words.size - 2){
            if(words[i].length + words[i+1].length >= 8) {// 현재 단어가 4글자 이면서 다음 단어가 3글자 이상인 경우 줄바꿈
                title += words[i]+"\n"
                Log.i("title1", "setNewLine: " + words[i] + ", "+ words[i+1] + ", title : " + title)

            }
            else {
                title += words[i]+" "
                Log.i("title2", "setNewLine: " + words[i] + ", "+ words[i+1] + ", title : " + title)
            }

//            if (words[i].length >= 5){ // 단어가 8글자 이상인 경우 줄바꿈
//                title += words[i]+"\n"
//            }
//            else if(words[i].length == 4 && words[i+1].length >= 3) {// 현재 단어가 4글자 이면서 다음 단어가 3글자 이상인 경우 줄바꿈
//                title += words[i]+"\n"
//            }
//
//            else if(words[i].length == 3 && words[i+1].length >= 4) {// 현재 단어가 3글자 이면서 다음 단어가 4글자 이상인 경우 줄바꿈
//                title += words[i]+"\n"
//            }
//            else if(words[i].length == 2 && words[i+1].length >= 5) {// 현재 단어가 2글자 이면서 다음 단어가 5글자 이상인 경우 줄바꿈
//                title += words[i]+"\n"
//            }
//            else{
//                title += words[i] + " "
//            }
        }

        title += words[words.size-1]
        Log.i("title3 : ", title)
            return title
    }
}