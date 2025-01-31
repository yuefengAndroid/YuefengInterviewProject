package com.example.yuefenginterviewproject.ui.popularactivities.subview2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.SubEvent01ViewModel
import com.example.yuefenginterviewproject.data.model.SubEvent02ViewModel
import com.example.yuefenginterviewproject.databinding.FragmentSubEvent01Binding
import com.example.yuefenginterviewproject.databinding.FragmentSubEvent02Binding


class SubEvent02Fragment : Fragment() {
    private lateinit var binding: FragmentSubEvent02Binding
    private val baseHomeModel: SubEvent02ViewModel by lazy {
        ViewModelProvider(this)[SubEvent02ViewModel::class.java] // 初始化 ViewModel
    }
    private lateinit var adapter: SubEvent01ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubEvent02Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.sub02ViewModel = baseHomeModel

        setupRecyclerView()
        observeViewModel()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = SubEvent01ListAdapter(this, arrayListOf()) // 先初始化为空列表
        binding.event02RecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SubEvent02Fragment.adapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel() {
        baseHomeModel.mySubHotList.observe(viewLifecycleOwner) { itemList ->
            adapter.list.clear()
            adapter.list.addAll(itemList)
            adapter.notifyDataSetChanged()
        }
    }

}