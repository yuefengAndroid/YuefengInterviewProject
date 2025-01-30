package com.example.yuefenginterviewproject.ui.messages

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.BaseHomeViewModel
import com.example.yuefenginterviewproject.data.model.MessagesViewModel
import com.example.yuefenginterviewproject.databinding.ActivityMessagesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MessagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesBinding
    private val messagesViewModel: MessagesViewModel by lazy {
        ViewModelProvider(this).get(MessagesViewModel::class.java) // 初始化 ViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_messages)
        binding.baseModel = messagesViewModel

        binding.iconBack.setOnClickListener {
            val intent = Intent().apply {
                putExtra("GO_TO_HOME_TAB", true) // 向 MainHomeActivity 传递数据
            }
            setResult(Activity.RESULT_OK, intent) // 设置结果
            finish() // 结束当前 Activity
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        messagesViewModel.fragmentListLiveData.observe(this) { fragments ->
            messagesViewModel.tabTitlesLiveData.observe(this) { tabTitles ->
                setupTabLayoutAndViewPager(fragments, tabTitles)
            }
        }

    }

    private fun setupTabLayoutAndViewPager(fragments: List<Fragment>, tabTitles: List<String>) {
        // 設置 ViewPager2 適配器
        val adapter = MessagesPagerAdapter(this, fragments)
        binding.messageViewpager.adapter = adapter
        binding.messageViewpager.isUserInputEnabled = false // 禁用滑动

        // 使用 TabLayoutMediator 綁定 TabLayout 和 ViewPager2
        TabLayoutMediator(binding.messageTabLayout, binding.messageViewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        binding.messageTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    binding.messageViewpager.setCurrentItem(it.position, false) // 這裡關閉切換動畫
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}