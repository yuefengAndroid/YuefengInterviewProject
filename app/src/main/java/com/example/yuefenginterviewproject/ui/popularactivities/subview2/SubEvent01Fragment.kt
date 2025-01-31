package com.example.yuefenginterviewproject.ui.popularactivities.subview2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yuefenginterviewproject.data.model.SubEvent01ViewModel
import com.example.yuefenginterviewproject.databinding.FragmentSubEvent01Binding

class SubEvent01Fragment : Fragment() {
    private lateinit var binding: FragmentSubEvent01Binding
    private val baseHomeModel: SubEvent01ViewModel by lazy {
        ViewModelProvider(this)[SubEvent01ViewModel::class.java] // 初始化 ViewModel
    }
    private lateinit var adapter: SubEvent01ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubEvent01Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.sub01ViewModel = baseHomeModel

        setupRecyclerView()
        observeViewModel()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = SubEvent01ListAdapter(this, arrayListOf()) // 先初始化为空列表
        binding.event01RecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SubEvent01Fragment.adapter
        }
    }

    private fun observeViewModel() {
        baseHomeModel.mySubHotList.observe(viewLifecycleOwner) { itemList ->
            adapter.list.clear()
            adapter.list.addAll(itemList)
            adapter.notifyDataSetChanged()
        }
    }

}