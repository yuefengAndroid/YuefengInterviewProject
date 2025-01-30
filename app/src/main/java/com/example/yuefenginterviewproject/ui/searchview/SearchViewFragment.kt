package com.example.yuefenginterviewproject.ui.searchview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yuefenginterviewproject.ColorFactory
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.SearchViewModel
import com.example.yuefenginterviewproject.databinding.FragmentSearchViewBinding


class SearchViewFragment : Fragment() {
    private lateinit var binding: FragmentSearchViewBinding
    private val baseHomeModel: SearchViewModel by lazy {
        ViewModelProvider(this)[SearchViewModel::class.java] // 初始化 ViewModel
    }
    private lateinit var adapter: SearchRecommendListAdapter
    private var isRecommendSelected = true // 記錄當前選擇的列表


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchViewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者

        binding.tcTagMore.maxLines = 3
        binding.tcTagMore.theme = ColorFactory.NONE
        binding.tcTagMore.tagMaxLength = 5

        binding.tcTagMore2.maxLines = 3
        binding.tcTagMore2.theme = ColorFactory.NONE
        binding.tcTagMore2.tagMaxLength = 5

        context?.let { ctx ->
            binding.tcTagMore.tagBackgroundColor = ContextCompat.getColor(ctx, R.color.color_ededed)
            binding.tcTagMore.tagTextColor = ContextCompat.getColor(ctx, R.color.color_272727)
            binding.tcTagMore.tagBorderColor = ContextCompat.getColor(ctx, R.color.color_ededed)

            binding.tcTagMore2.tagBackgroundColor = ContextCompat.getColor(ctx, R.color.color_ededed)
            binding.tcTagMore2.tagTextColor = ContextCompat.getColor(ctx, R.color.color_272727)
            binding.tcTagMore2.tagBorderColor = ContextCompat.getColor(ctx, R.color.color_ededed)
        }
        setupRecyclerView()
        observeViewModel()

        binding.layoutRecommend.setOnClickListener {
            showRecommendList()
        }

        binding.layoutHottopic.setOnClickListener {
            showHotTopicList()
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = SearchRecommendListAdapter(this, arrayListOf()) // 初始 Adapter，空列表
        binding.rvContainer.layoutManager = LinearLayoutManager(requireContext())
        binding.rvContainer.adapter = adapter
    }
    private fun observeViewModel() {
        // 觀察 ViewModel 的 LiveData
        baseHomeModel.searchListsLiveData.observe(viewLifecycleOwner) { tags ->
            val (list1, list2) = tags
            binding.tcTagMore.tags = list1
            binding.tcTagMore2.tags = list2
        }

        baseHomeModel.myProductsList.observe(viewLifecycleOwner) { products ->
            if (isRecommendSelected) {
                adapter.list.clear()
                adapter.list.addAll(products)
                adapter.notifyDataSetChanged()
            }
        }

        baseHomeModel.myProductsList2.observe(viewLifecycleOwner) { products ->
            if (!isRecommendSelected) {
                adapter.list.clear()
                adapter.list.addAll(products)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun showRecommendList() {
        isRecommendSelected = true
        updateUI(isRecommendSelected)
        adapter.list.clear()
        adapter.list.addAll(baseHomeModel.myProductsList.value ?: emptyList())
        adapter.notifyDataSetChanged()
    }

    fun showHotTopicList() {
        isRecommendSelected = false
        updateUI(isRecommendSelected)
        adapter.list.clear()
        adapter.list.addAll(baseHomeModel.myProductsList2.value ?: emptyList())
        adapter.notifyDataSetChanged()
    }

    private fun updateUI(isRecommend: Boolean) {
        if (isRecommend) {
            binding.layoutRecommend.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_search_tab)
            binding.ivRecommend.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.icon_vip_fff_20))
            binding.tvRecommend.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_ffffff))

            binding.layoutHottopic.background = null
            binding.ivHottopic.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.icon_solid_fire_default_20))
            binding.tvHottopic.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_323232))
        } else {
            binding.layoutHottopic.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_search_tab)
            binding.ivHottopic.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.icon_solid_fire_fff_24))
            binding.tvHottopic.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_ffffff))

            binding.layoutRecommend.background = null
            binding.ivRecommend.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.icon_vip_default_21))
            binding.tvRecommend.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_323232))
        }
    }

}