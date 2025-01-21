package com.example.yuefenginterviewproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.yuefenginterviewproject.databinding.ActivityMainHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainHomeBinding
    private lateinit var adapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_home)

        // 初始化 ViewPager2 和 TabLayout
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        adapter = MainPagerAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false // 禁用滑动

        // 设置 TabLayout 与 ViewPager2 关联
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = createCustomTab(position)
        }.attach()

        // 设置 TabLayout 的 Tab 选中监听器
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // 点击 Tab 时切换页面
                binding.viewPager.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // No-op
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // No-op
            }
        })

    }

    private fun setupTabLayout() {
        // 可选：自定义 TabLayout 的样式，例如选中时的文字颜色
        binding.tabLayout.apply {
            tabGravity = TabLayout.GRAVITY_FILL
            tabMode = TabLayout.MODE_FIXED
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun createCustomTab(position: Int): View {
        val tabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null)
        val tabIcon = tabView.findViewById<ImageView>(R.id.tab_icon)
        val tabText = tabView.findViewById<TextView>(R.id.tab_text)

        when (position) {
            0 -> {
                tabIcon.setImageResource(R.drawable.ic_home_black_24dp)
                tabText.text = getString(R.string.title_home)
            }
            1 -> {
                tabIcon.setImageResource(R.drawable.ic_bestsellers_24)
                tabText.text = getString(R.string.title_bestsellers)
            }
            2 -> {
                tabIcon.setImageResource(R.drawable.ic_hot_event_24)
                tabText.text = getString(R.string.title_popularActivities)
            }
            3 -> {
                tabIcon.setImageResource(R.drawable.ic_tv_hot_24)
                tabText.text = getString(R.string.title_tvHot)
            }
            4 -> {
                tabIcon.setImageResource(R.drawable.ic_shopping_cart_24)
                tabText.text = getString(R.string.title_cart)
            }
            5 -> {
                tabIcon.setImageResource(R.drawable.ic_member_24)
                tabText.text = getString(R.string.title_member)
            }
        }

        return tabView
    }
}


