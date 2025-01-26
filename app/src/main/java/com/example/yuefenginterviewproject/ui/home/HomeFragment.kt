package com.example.yuefenginterviewproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yuefenginterviewproject.data.model.HomeViewModel
import com.example.yuefenginterviewproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java) // 初始化 ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.homeModel = homeViewModel // 將 ViewModel 綁定到 Binding

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        // 觀察 ViewModel 的 LiveData
        homeViewModel.someLiveData.observe(viewLifecycleOwner) { data ->
            // 更新 UI
            binding.textHome.text = data
        }
    }
}