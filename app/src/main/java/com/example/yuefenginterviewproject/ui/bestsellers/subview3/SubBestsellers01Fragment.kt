package com.example.yuefenginterviewproject.ui.bestsellers.subview3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yuefenginterviewproject.data.model.SubBestsellers01ViewModel
import com.example.yuefenginterviewproject.databinding.FragmentSubBestsellers01Binding

class SubBestsellers01Fragment : Fragment() {
    private lateinit var binding: FragmentSubBestsellers01Binding
    private val baseHomeModel: SubBestsellers01ViewModel by lazy {
        ViewModelProvider(this)[SubBestsellers01ViewModel::class.java] // 初始化 ViewModel
    }
    private lateinit var adapter: SubBestsellers01Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubBestsellers01Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.sub01ViewModel = baseHomeModel

        setupRecyclerView() // 初始化 RecyclerView
        observeData() // 监听数据变化

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = SubBestsellers01Adapter(this, arrayListOf()) // 初始化时传入空列表
        binding.SubBestsellers01RecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext()) // 纵向列表布局
            adapter = this@SubBestsellers01Fragment.adapter
        }
    }

    private fun observeData() {
        baseHomeModel.mySubBestsellers01List.observe(viewLifecycleOwner) { itemList ->
            adapter.list.clear() // 清空旧数据
            adapter.list.addAll(itemList) // 添加新数据
            adapter.notifyDataSetChanged() // 通知 UI 更新
        }
    }
}