package com.example.yuefenginterviewproject.ui.home.tabfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yuefenginterviewproject.data.model.HomeViewModel
import com.example.yuefenginterviewproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val baseHomeModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java] // 初始化 ViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.homeModel = baseHomeModel // 將 ViewModel 綁定到 Binding

        observeData()  // 監聽 LiveData
        return binding.root
    }

    private fun observeData() {
        baseHomeModel.mySubHotList.observe(viewLifecycleOwner) { itemList ->
            // 讓 Banner Adapter 更新數據
            binding.homePopularAdsBanner.adapter?.let { adapter ->
                (adapter as HomeAdBannerItemAdapter).setList(itemList, baseHomeModel)
            }
        }
    }
}