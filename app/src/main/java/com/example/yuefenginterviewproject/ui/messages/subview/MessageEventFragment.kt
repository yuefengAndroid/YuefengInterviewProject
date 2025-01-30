package com.example.yuefenginterviewproject.ui.messages.subview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yuefenginterviewproject.data.model.MessageEventViewModel
import com.example.yuefenginterviewproject.databinding.FragmentMessageEventBinding

class MessageEventFragment : Fragment() {
    private lateinit var binding: FragmentMessageEventBinding
    private val baseEventModel: MessageEventViewModel by lazy {
        ViewModelProvider(this).get(MessageEventViewModel::class.java) // 初始化 ViewModel
    }
    private lateinit var adapter: MessageEventListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageEventBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.baseModel = baseEventModel

        setupRecyclerView()
        observeViewModel()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = MessageEventListAdapter(this, arrayListOf()) // 初始化 Adapter
        binding.messageRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.messageRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        baseEventModel.myEventList.observe(viewLifecycleOwner) { events ->
            adapter.list.clear() // 清空舊數據
            adapter.list.addAll(events) // 加入新數據
            adapter.notifyDataSetChanged() // 更新 RecyclerView
        }
    }

}