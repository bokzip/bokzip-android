package com.unionz.bokzip

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.unionz.bokzip.adapter.LocationItemAdapter
import com.unionz.bokzip.viewmodel.FilterViewModel
import kotlinx.android.synthetic.main.bottom_sheet_filter.*
import com.unionz.bokzip.databinding.BottomSheetFilterBinding
import com.unionz.bokzip.model.type.FilterCategory
import com.unionz.bokzip.model.type.FilterSortOption

class FilterBottomSheet : BottomSheetDialogFragment() {
    private lateinit var adapter: LocationItemAdapter
    private var handler: Handler = Handler(Looper.getMainLooper())
    private lateinit var workRunnable: Runnable
    private val DELAY: Long = 500
    private val viewModel: FilterViewModel by activityViewModels()
    private lateinit var binding: BottomSheetFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetFilterBinding.inflate(inflater, container, false)

        setListener()

        return binding.root
    }

    private fun setListener() {
        binding.locationRecyclerview.setHasFixedSize(true)
        binding.locationRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = LocationItemAdapter(requireContext())
        binding.locationRecyclerview.adapter = adapter

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

        // 필터 - 카테고리
        val rgCategoryOne: RadioGroup = binding.categoryRadioGroupOne
        val rgCategoryTwo: RadioGroup = binding.categoryRadioGroupTwo

        // 필터 - 카테고리 윗줄
        if (rgCategoryOne != null) {
            binding.categoryRadioGroupOne.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    education.id -> {
                        if (education.isChecked)
                            rgCategoryTwo.clearCheck()
                        viewModel.setFilterCategory(getString(FilterCategory.EDUCATION.strResId))
                    }
                    employment.id -> {
                        if (employment.isChecked)
                            rgCategoryTwo.clearCheck()
                        viewModel.setFilterCategory(getString(FilterCategory.EMPLOYMENT.strResId))
                    }
                    health.id -> {
                        if (health.isChecked)
                            rgCategoryTwo.clearCheck()
                        viewModel.setFilterCategory(getString(FilterCategory.HEALTH.strResId))
                    }
                }
            }
        }

        // 필터 - 카테고리 아랫줄
        if (rgCategoryTwo != null) {
            binding.categoryRadioGroupTwo.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    life.id -> {
                        if (life.isChecked)
                            rgCategoryOne.clearCheck()
                        viewModel.setFilterCategory(getString(FilterCategory.LIFE.strResId))
                    }
                    all.id -> {
                        if (all.isChecked)
                            rgCategoryOne.clearCheck()
                        viewModel.setFilterCategory(getString(FilterCategory.ALL.strResId))
                    }
                }
            }
        }

        // 필터 - 거주지
        binding.locationText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val keyword = s.toString()
                workRunnable = Runnable { adapter.filter(keyword) }
                handler.removeCallbacks(workRunnable)
                handler.postDelayed(workRunnable, DELAY)
            }
        })

        // 필터 - 정렬
        val rgSort: RadioGroup = binding.sortRadioGroup
        if (rgSort != null) {
            rgSort.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    star_count_order.id -> {
                        viewModel.setSortOption(getString(FilterSortOption.STAR_COUNT.strResId))
                    }
                    view_count_order.id -> {
                        viewModel.setSortOption(getString(FilterSortOption.VIEW_COUNT.strResId))
                    }
                }
            }
        }

        binding.delete.setOnClickListener {
            binding.locationText.text = null
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