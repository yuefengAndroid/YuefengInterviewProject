package com.example.yuefenginterviewproject.ui.tvhot.tvsubview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yuefenginterviewproject.data.model.TvSub01ViewModel
import com.example.yuefenginterviewproject.databinding.FragmentTvSub01Binding

class TvSub01Fragment : Fragment() {
    private lateinit var binding: FragmentTvSub01Binding
    private val baseHomeModel: TvSub01ViewModel by lazy {
        ViewModelProvider(this)[TvSub01ViewModel::class.java] // 初始化 ViewModel
    }
    private lateinit var adapter: TvSubAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvSub01Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.sub01ViewModel = baseHomeModel

        setupRecyclerView() // 初始化 RecyclerView
        observeData() // 监听数据变化

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = TvSubAdapter(this, arrayListOf()) // 初始化时传入空列表
        binding.SubBestsellers01RecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2) // 2 列的网格布局
            adapter = this@TvSub01Fragment.adapter
        }
    }

    private fun observeData() {
        baseHomeModel.myTvSubList.observe(viewLifecycleOwner) { itemList ->
            adapter.list.clear() // 清空旧数据
            adapter.list.addAll(itemList) // 添加新数据
            adapter.notifyDataSetChanged() // 通知 UI 更新
        }
    }
}