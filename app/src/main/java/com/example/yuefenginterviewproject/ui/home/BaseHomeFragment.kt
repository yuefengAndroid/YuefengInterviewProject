package com.example.yuefenginterviewproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.BaseHomeViewModel
import com.example.yuefenginterviewproject.databinding.FragmentBaseHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class BaseHomeFragment : Fragment() {

    private lateinit var binding: FragmentBaseHomeBinding
    private val baseHomeModel: BaseHomeViewModel by lazy {
        ViewModelProvider(this).get(BaseHomeViewModel::class.java) // 初始化 ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.baseModel = baseHomeModel // 將 ViewModel 綁定到 Binding

        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        // 觀察 ViewModel 的 LiveData
        baseHomeModel.fragmentListLiveData.observe(viewLifecycleOwner) { fragments ->
            baseHomeModel.tabTitlesLiveData.observe(viewLifecycleOwner) { tabTitles ->
                setupTabLayoutAndViewPager(fragments, tabTitles)
            }
        }
    }

    private fun setupTabLayoutAndViewPager(fragments: List<Fragment>, tabTitles: List<String>) {
        // 設置 ViewPager2 適配器
        val adapter = BaseHomePagerAdapter(this, fragments)
        binding.homeViewpager.adapter = adapter

        // 使用 TabLayoutMediator 綁定 TabLayout 和 ViewPager2
        TabLayoutMediator(binding.homeTabLayout, binding.homeViewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        // 自定義 TabLayout 選中顏色和下底線顏色 (如果在XML有設定,則以下設定顏色程式可以省略)
        binding.homeTabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.red, null))
        binding.homeTabLayout.setTabTextColors(
            resources.getColor(R.color.black, null),
            resources.getColor(R.color.red, null)
        )
    }

}