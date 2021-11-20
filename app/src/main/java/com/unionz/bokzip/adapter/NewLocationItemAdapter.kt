package com.unionz.bokzip.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.unionz.bokzip.databinding.ItemLocationBtnBinding
import com.unionz.bokzip.viewmodel.FilterViewModel
import androidx.recyclerview.widget.RecyclerView as RecyclerView

class NewLocationItemAdapter(private val viewModel: FilterViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var locations = mutableListOf<String>()
    private lateinit var isSelected: Array<Boolean>
    private var lastPosition: Int? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding =
            ItemLocationBtnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CustomViewHolder).bind(position)
        holder.binding.locationBtn.setOnClickListener {
            isSelected[position] = !isSelected[position]
            if (isSelected[position]) {
                lastPosition?.let { isSelected[it] = false }
                viewModel.setFilterLocation(locations[position])
            } else {
                viewModel.setFilterLocation(null)
            }

            notifyItemChanged(position)
            lastPosition?.let { notifyItemChanged(it) }
            lastPosition = position
        }
    }

    inner class CustomViewHolder(val binding: ItemLocationBtnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                locationBtn.text = locations[position]
                locationBtn.isSelected = isSelected[position]

            }
        }
    }

    override fun getItemCount() = locations.size

    fun setData(locations: Array<String>) {
        this.locations.clear()
        this.locations.addAll(locations)
        isSelected = Array(locations.size) { false }
        notifyDataSetChanged()
    }
}
