package com.unionz.bokzip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.unionz.bokzip.adapter.FilterItemAdapter
import com.unionz.bokzip.viewmodel.FilterViewModel
import kotlinx.android.synthetic.main.bottom_sheet_filter.*
import com.unionz.bokzip.databinding.BottomSheetFilterBinding
import com.unionz.bokzip.model.type.FilterOption
import com.unionz.bokzip.model.type.FilterSortOption

class FilterBottomSheet : BottomSheetDialogFragment() {
    private val viewModel: FilterViewModel by activityViewModels()
    private lateinit var binding: BottomSheetFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetFilterBinding.inflate(inflater, container, false)

        initializeView()
        setListener()

        return binding.root
    }

    private fun initializeView() {
        val categoryItemAdapter = FilterItemAdapter(requireContext(), viewModel, FilterOption.CATEGORY)
        FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
        }.let {
            binding.categoryBtn.layoutManager = it
            binding.categoryBtn.adapter = categoryItemAdapter
        }
        val categories = listOf(
            R.string.category_short_education,
            R.string.category_short_employment,
            R.string.category_short_health,
            R.string.category_short_life,
            R.string.category_all
        )

        categoryItemAdapter.setIntData(categories)

        val locationItemAdapter = FilterItemAdapter(requireContext(), viewModel, FilterOption.LOCATION)
        FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
        }.let {
            binding.locationBtn.layoutManager = it
            binding.locationBtn.adapter = locationItemAdapter
        }
        val locations = resources.getStringArray(R.array.location_array)
        locationItemAdapter.setStrData(locations)
    }

    private fun setListener() {
        binding.category.isSelected = true

        binding.category.setOnClickListener {
            binding.filterLocation.visibility = View.GONE
            location.isSelected = false
            binding.filterSort.visibility = View.GONE
            sort.isSelected = false
            binding.filterCategory.visibility = View.VISIBLE
            category.isSelected = true
        }
        binding.location.setOnClickListener {
            binding.filterCategory.visibility = View.GONE
            category.isSelected = false
            binding.filterSort.visibility = View.GONE
            sort.isSelected = false
            binding.filterLocation.visibility = View.VISIBLE
            location.isSelected = true
        }
        binding.sort.setOnClickListener {
            binding.filterCategory.visibility = View.GONE
            category.isSelected = false
            binding.filterLocation.visibility = View.GONE
            location.isSelected = false
            binding.filterSort.visibility = View.VISIBLE
            sort.isSelected = true
        }

        // 필터 - 정렬
        binding.sortRadioGroup?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.starCountOrder.id -> {
                    viewModel.setSortOption(getString(FilterSortOption.STAR_COUNT.strResId))
                }
                binding.viewCountOrder.id -> {
                    viewModel.setSortOption(getString(FilterSortOption.VIEW_COUNT.strResId))
                }
            }
        }

        binding.apply.setOnClickListener {
            viewModel.setCompleted(true)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }
    }
}