package com.unionz.bokzip.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.unionz.bokzip.adapter.RecommendBokjiItemAdapter
import com.unionz.bokzip.databinding.FragmentTapScrapBinding
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.model.ScrapBokjiInfo
import com.unionz.bokzip.model.type.TabName
import com.unionz.bokzip.viewmodel.FilterViewModel
import kotlinx.android.synthetic.main.fragment_tap_scrap.*

class ScrapFragment : Fragment() {
    private val TAG: String = "스크랩 탭"
    private val viewModel: FilterViewModel by activityViewModels()
    private lateinit var binding: FragmentTapScrapBinding
    private lateinit var adapter: RecommendBokjiItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTapScrapBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        setListener()

        return binding.root
    }

    private fun setListener() {
        viewModel.getScrapItems().observe(viewLifecycleOwner) {
            initializeView(it)
        }
    }

    private fun initializeView(bokjiList: ArrayList<ScrapBokjiInfo>?) {
        var list = ArrayList<RecommendBokjiItem>()
        bokjiList?.let {
            for (item in bokjiList) {
                if (item.post != null)
                    list.add(
                        RecommendBokjiItem(
                            item.post.id,
                            item.post.title,
                            item.post.category,
                            item.post.thumbnail,
                            item.post.isScrap
                        )
                    )
                else
                    list.add(
                        RecommendBokjiItem(
                            item.general.id,
                            item.general.title,
                            item.general.category,
                            item.general.thumbnail,
                            item.general.isScrap
                        )
                    )
            }
        }

        adapter = RecommendBokjiItemAdapter(requireContext(), list, viewModel, TabName.SCRAP)
        recyclerview.adapter = adapter
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerview.layoutManager = gridLayoutManager
        binding.count.text = bokjiList?.size.toString()
    }
}