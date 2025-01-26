package com.example.yuefenginterviewproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.HomeViewModel
import com.example.yuefenginterviewproject.databinding.FragmentBaseHomeBinding
import com.example.yuefenginterviewproject.ui.cart.CartFragment
import com.google.android.material.tabs.TabLayoutMediator

class BaseHomeFragment : Fragment() {

    private lateinit var binding: FragmentBaseHomeBinding
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java) // 初始化 ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.homeModel = homeViewModel // 將 ViewModel 綁定到 Binding

        observeViewModel()
        setupTabLayoutAndViewPager()
        return binding.root
    }

    private fun observeViewModel() {
        // 觀察 ViewModel 的 LiveData
        homeViewModel.someLiveData.observe(viewLifecycleOwner) { data ->
            // 更新 UI

        }
    }

    private fun setupTabLayoutAndViewPager() {
        val fragmentList = listOf(
            CartFragment(),
            CartFragment(),
            CartFragment(),
            CartFragment(),
            CartFragment(),
            CartFragment(),
            CartFragment(),
            CartFragment(),
            CartFragment(),
            CartFragment()
        )

        val tabTitles = listOf(
            "首頁", "直播", "電視購物", "保健/醫療", "美食/票券",
            "保養/彩妝", "旅遊/住宿券", "日用清潔", "生活家電", "寵物"
        )
        // 設置 ViewPager2 適配器
        val adapter = BaseHomePagerAdapter(this, fragmentList)
        binding.homeViewpager.adapter = adapter

        // 使用 TabLayoutMediator 綁定 TabLayout 和 ViewPager2
        TabLayoutMediator(binding.homeTabLayout, binding.homeViewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        // 自定義 TabLayout 選中顏色和下底線顏色 (如果在XML有設定,則以下設定顏色程式可以省略)
        binding.homeTabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.red, null))
        binding.homeTabLayout.setTabTextColors(
            resources.getColor(R.color.black, null), // 未選中文字顏色
            resources.getColor(R.color.red, null)   // 選中文字顏色
        )
    }
}