package com.unionz.bokzip.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unionz.bokzip.FilterBottomSheet
import com.unionz.bokzip.R
import kotlinx.android.synthetic.main.fragment_tap_recommend.view.*

class RecommendFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_tap_recommend, container, false)
        view.btn_filter.setOnClickListener {
            val bottomSheet = FilterBottomSheet()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag);
        }
        return view
    }
}