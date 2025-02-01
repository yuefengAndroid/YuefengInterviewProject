package com.example.yuefenginterviewproject.ui.home.tabfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yuefenginterviewproject.data.model.HomeViewModel
import com.example.yuefenginterviewproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val baseHomeModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java] // 初始化 ViewModel
    }
    private lateinit var adapter: HomeBanner2Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.homeModel = baseHomeModel // 將 ViewModel 綁定到 Binding

        observeData()  // 監聽 LiveData
        setupRecyclerView() // 初始化 RecyclerView
        return binding.root
    }

    private fun observeData() {
        baseHomeModel.mySubHotList.observe(viewLifecycleOwner) { itemList ->
            // 讓 Banner Adapter 更新數據
            binding.homePopularAdsBanner.adapter?.let { adapter ->
                (adapter as HomeAdBannerItemAdapter).setList(itemList, baseHomeModel)
            }
        }

        baseHomeModel.homeBannerList.observe(viewLifecycleOwner) { bannerList ->
            adapter.setList(bannerList) // 更新 RecyclerView 資料
        }
    }

    private fun setupRecyclerView() {
        adapter = HomeBanner2Adapter(this, arrayListOf()) // 初始化时传入空列表
        binding.banner2RecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false) // 設置為橫向
            adapter = this@HomeFragment.adapter
            overScrollMode = View.OVER_SCROLL_NEVER // 防止滾動時出現反彈效果
        }
    }
}