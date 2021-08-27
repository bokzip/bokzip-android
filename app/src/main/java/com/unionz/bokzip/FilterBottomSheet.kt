package com.unionz.bokzip

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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

        view.category.setOnClickListener {
            view.filter_location.visibility = View.GONE
            view.location.setBackgroundResource(R.drawable.selector_tab_menu_bg)
            view.location.setTextColor(R.drawable.selector_tab_menu_text_color)
            view.filter_sort.visibility = View.GONE
            view.sort.setBackgroundResource(R.drawable.selector_tab_menu_bg)
            view.sort.setTextColor(R.drawable.selector_tab_menu_text_color)

            view.filter_category.visibility = View.VISIBLE
            view.category.setBackgroundResource(R.drawable.btn_default_bg)
            view.category.setTextColor(Color.WHITE)
        }
        view.location.setOnClickListener {
            view.filter_category.visibility = View.GONE
            view.category.setBackgroundResource(R.drawable.selector_tab_menu_bg)
            view.category.setTextColor(R.drawable.selector_tab_menu_text_color)
            view.filter_sort.visibility = View.GONE
            view.sort.setBackgroundResource(R.drawable.selector_tab_menu_bg)
            view.sort.setTextColor(R.drawable.selector_tab_menu_text_color)

            view.filter_location.visibility = View.VISIBLE
            view.location.setBackgroundResource(R.drawable.btn_default_bg)
            view.location.setTextColor(Color.WHITE)
        }
        view.sort.setOnClickListener {
            view.filter_category.visibility = View.GONE
            view.category.setBackgroundResource(R.drawable.selector_tab_menu_bg)
            view.category.setTextColor(R.drawable.selector_tab_menu_text_color)
            view.filter_location.visibility = View.GONE
            view.location.setBackgroundResource(R.drawable.selector_tab_menu_bg)
            view.location.setTextColor(R.drawable.selector_tab_menu_text_color)

            view.filter_sort.visibility = View.VISIBLE
            view.sort.setBackgroundResource(R.drawable.btn_default_bg)
            view.sort.setTextColor(Color.WHITE)
        }
        return view
    }


}