package com.unionz.bokzip.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unionz.bokzip.model.type.GeneralBokjiCategory.*
import com.unionz.bokzip.adapter.GeneralBokjiItemAdapter
import com.unionz.bokzip.databinding.FragmentTapGeneralBinding
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.viewmodel.FilterViewModel

class GeneralFragment : Fragment() {
    private val TAG = "일반 탭"
    private val viewModel: FilterViewModel by activityViewModels()
    private lateinit var binding: FragmentTapGeneralBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTapGeneralBinding.inflate(inflater, container, false)
        initializeView()

        return binding.root
    }

    private fun initializeView() {
        val bokjiList = viewModel.getGeneralItems().value

        var healthMedical = ArrayList<RecommendBokjiItem>()
        var culture = ArrayList<RecommendBokjiItem>()
        var transportation = ArrayList<RecommendBokjiItem>()
        var digital = ArrayList<RecommendBokjiItem>()
        var childCare = ArrayList<RecommendBokjiItem>()
        var work = ArrayList<RecommendBokjiItem>()
        var etc = ArrayList<RecommendBokjiItem>()

        bokjiList?.let {
            for (i: Int in 0 until bokjiList.size) {
                when (bokjiList[i].category) {
                    getString(HEALTH_MEDICAL.strResId) -> healthMedical.add(bokjiList[i])
                    getString(CULTURE.strResId) -> culture.add(bokjiList[i])
                    getString(TRANSPORTATION.strResId) -> transportation.add(bokjiList[i])
                    getString(DIGITAL.strResId) -> digital.add(bokjiList[i])
                    getString(CHILD_CARE.strResId) -> childCare.add(bokjiList[i])
                    getString(WORK.strResId) -> work.add(bokjiList[i])
                    getString(ETC.strResId) -> etc.add(bokjiList[i])
                }
            }

            setAdapter(
                binding.healthRecyclerview,
                GeneralBokjiItemAdapter(requireContext(), viewModel),
                healthMedical
            )
            setAdapter(
                binding.cultureRecyclerview,
                GeneralBokjiItemAdapter(requireContext(), viewModel),
                culture
            )
            setAdapter(
                binding.transportationRecyclerview,
                GeneralBokjiItemAdapter(requireContext(), viewModel),
                transportation
            )
            setAdapter(
                binding.itRecyclerview,
                GeneralBokjiItemAdapter(requireContext(), viewModel),
                digital
            )
            setAdapter(
                binding.careRecyclerview,
                GeneralBokjiItemAdapter(requireContext(), viewModel),
                childCare
            )
            setAdapter(
                binding.workRecyclerview, GeneralBokjiItemAdapter(requireContext(), viewModel), work
            )
            setAdapter(
                binding.etcRecyclerview, GeneralBokjiItemAdapter(requireContext(), viewModel), etc
            )
        }
    }

    private fun setAdapter(
        recyclerView: RecyclerView,
        generalBokjiadapter: GeneralBokjiItemAdapter,
        dataSet: ArrayList<RecommendBokjiItem>
    ) {
        generalBokjiadapter.setData(dataSet)
        recyclerView.adapter = generalBokjiadapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }
}