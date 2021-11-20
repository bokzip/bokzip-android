package com.unionz.bokzip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.unionz.bokzip.R
import com.unionz.bokzip.databinding.ItemFilterCategoryBtnBinding
import com.unionz.bokzip.databinding.ItemFilterLocationBtnBinding
import com.unionz.bokzip.model.type.FilterCategory
import com.unionz.bokzip.model.type.FilterOption
import com.unionz.bokzip.viewmodel.FilterViewModel
import androidx.recyclerview.widget.RecyclerView as RecyclerView

class FilterItemAdapter(
    private val context: Context,
    private val viewModel: FilterViewModel,
    private val type: FilterOption
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var intDataSet: List<Int>
    private lateinit var strDataSet: Array<String>
    private lateinit var isSelected: Array<Boolean>
    private var lastPosition: Int? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (type == FilterOption.CATEGORY) {
            val binding =
                ItemFilterCategoryBtnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CategoryViewHolder(binding)
        } else {
            val binding =
                ItemFilterLocationBtnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LocationViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> holder.bind(position)
            is LocationViewHolder -> holder.bind(position)
        }
    }

    inner class CategoryViewHolder(val binding: ItemFilterCategoryBtnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                categoryBtn.text = context.getString(intDataSet[position])
                categoryBtn.isSelected = isSelected[position]
                categoryBtn.setOnClickListener {
                    isSelected[position] = !isSelected[position]
                    if (isSelected[position]) {
                        lastPosition?.let { isSelected[it] = false }
                        val category = when (intDataSet[position]) {
                            R.string.category_short_education -> context.getString(FilterCategory.EDUCATION.strResId)
                            R.string.category_short_employment -> context.getString(FilterCategory.EMPLOYMENT.strResId)
                            R.string.category_short_health -> context.getString(FilterCategory.HEALTH.strResId)
                            R.string.category_short_life -> context.getString(FilterCategory.LIFE.strResId)
                            else -> context.getString(FilterCategory.ALL.strResId)
                        }
                        viewModel.setFilterCategory(category)
                    } else {
                        viewModel.setFilterLocation(null)
                    }

                    notifyItemChanged(position)
                    lastPosition?.let { notifyItemChanged(it) }
                    lastPosition = position
                }
            }
        }
    }

    inner class LocationViewHolder(val binding: ItemFilterLocationBtnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                locationBtn.text = strDataSet[position]
                locationBtn.isSelected = isSelected[position]
                locationBtn.setOnClickListener {
                    isSelected[position] = !isSelected[position]
                    if (isSelected[position]) {
                        lastPosition?.let { isSelected[it] = false }
                        viewModel.setFilterLocation(strDataSet[position])
                    } else {
                        viewModel.setFilterLocation(null)
                    }

                    notifyItemChanged(position)
                    lastPosition?.let { notifyItemChanged(it) }
                    lastPosition = position
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (type == FilterOption.CATEGORY)
            intDataSet.size
        else
            strDataSet.size
    }

    fun setIntData(data: List<Int>) {
        intDataSet = data
        isSelected = Array(data.size) { false }
        notifyDataSetChanged()
    }

    fun setStrData(data: Array<String>) {
        strDataSet = data
        isSelected = Array(data.size) { false }
        notifyDataSetChanged()
    }
}
