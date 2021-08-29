package com.unionz.bokzip

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_filter.*
import kotlinx.android.synthetic.main.bottom_sheet_filter.view.*

class FilterBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.bottom_sheet_filter, container, false)
        view.back.setOnClickListener {
            dismiss()
            //수정.
        }
        view.category.isSelected=true

        view.category.setOnClickListener {
            view.filter_location.visibility = View.GONE
            location.isSelected=false
            view.filter_sort.visibility = View.GONE
            sort.isSelected=false
            view.filter_category.visibility = View.VISIBLE
            category.isSelected=true
        }
        view.location.setOnClickListener {
            view.filter_category.visibility = View.GONE
            category.isSelected=false
            view.filter_sort.visibility = View.GONE
            sort.isSelected=false
            view.filter_location.visibility = View.VISIBLE
            location.isSelected=true
        }
        view.sort.setOnClickListener {
            view.filter_category.visibility = View.GONE
            category.isSelected=false
            view.filter_location.visibility = View.GONE
            location.isSelected=false
            view.filter_sort.visibility = View.VISIBLE
            sort.isSelected=true
        }

        //필터 - 카테고리
        val rgCategoryOne : RadioGroup = view.category_radio_group_one
        val rgCategoryTwo : RadioGroup = view.category_radio_group_two
        var categoryResult : String = "선택된 카테고리"

        //필터 - 카테고리 윗줄
        if(rgCategoryOne != null){
            rgCategoryOne.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    education.id -> {
                        if (education.isChecked)
                            rgCategoryTwo.clearCheck()
                        categoryResult = education.text.toString()
                        Toast.makeText(context, categoryResult, Toast.LENGTH_SHORT).show()
                    }
                    employment.id -> {
                        if (employment.isChecked)
                            rgCategoryTwo.clearCheck()
                        categoryResult = employment.text.toString()
                        Toast.makeText(context, categoryResult, Toast.LENGTH_SHORT).show()
                    }
                    health.id -> {
                        if (health.isChecked)
                            rgCategoryTwo.clearCheck()
                        categoryResult = health.text.toString()
                        Toast.makeText(context, categoryResult, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //필터 - 카테고리 아랫줄
       if(rgCategoryTwo != null){
            rgCategoryTwo.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    life.id -> {
                        if (life.isChecked)
                            rgCategoryOne.clearCheck()
                        categoryResult = life.text.toString()
                        Toast.makeText(context, categoryResult, Toast.LENGTH_SHORT).show()
                    }
                    all.id -> {
                        if (all.isChecked)
                            rgCategoryOne.clearCheck()
                        categoryResult = all.text.toString()
                        Toast.makeText(context, categoryResult, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //필터 - 정렬
        val rgSort : RadioGroup = view.sort_radio_group
        var sortResult : Int = 100
        if(rgSort != null){
            rgSort.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    star_count_order.id -> {
                        sortResult = 0
                        Toast.makeText(context, sortResult.toString(), Toast.LENGTH_SHORT).show()
                    }
                    view_count_order.id -> {
                        sortResult = 1
                        Toast.makeText(context, sortResult.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return view
    }


}