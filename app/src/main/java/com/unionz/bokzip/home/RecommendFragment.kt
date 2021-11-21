package com.unionz.bokzip.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.unionz.bokzip.FilterBottomSheet
import com.unionz.bokzip.adapter.RecommendBokjiItemAdapter
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.viewmodel.FilterViewModel
import kotlinx.android.synthetic.main.fragment_tap_recommend.*
import com.unionz.bokzip.databinding.FragmentTapRecommendBinding
import com.unionz.bokzip.model.type.TabName

class RecommendFragment : Fragment() {
    private val TAG = "추천 탭"
    private val viewModel: FilterViewModel by activityViewModels()
    private lateinit var binding: FragmentTapRecommendBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTapRecommendBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        setListener()
        return binding.root
    }

    private fun setListener() {
        binding.btnFilter.setOnClickListener {
            val bottomSheet = FilterBottomSheet()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag);
        }

        viewModel.getCompleted().observe(viewLifecycleOwner) { isCompleted ->
            if (isCompleted) {
                viewModel.getFilterBokjiItem()
            }
        }

        viewModel.getItems().observe(viewLifecycleOwner) { items ->
            items?.let {
                initializeView(it)
                viewModel.reset()
            }
        }
    }

    private fun initializeView(bokjiList: ArrayList<RecommendBokjiItem>) {
        val adapter = RecommendBokjiItemAdapter(requireContext(), bokjiList, viewModel, TabName.ALL)
        recyclerview.adapter = adapter
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerview.layoutManager = gridLayoutManager
        binding.categoryTextview.text =  (viewModel.getFilterLocation().value ?: "중앙부처") + " 복지"
    }
}