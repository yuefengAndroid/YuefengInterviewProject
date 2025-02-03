package com.example.yuefenginterviewproject.ui.home.tabfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.HomeViewModel
import com.example.yuefenginterviewproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val baseHomeModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java] // 初始化 ViewModel
    }
    private lateinit var adapter: HomeBanner2Adapter
    private lateinit var adapter3: HomeBanner3Adapter
    private lateinit var adapter5: HomeBanner5Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.homeModel = baseHomeModel // 將 ViewModel 綁定到 Binding

        observeData()  // 監聽 LiveData
        setupRecyclerView() // 初始化 RecyclerView

        val radioButtons = listOf(binding.rbTime, binding.rbGlobal, binding.rbShop)

        radioButtons.forEach { button ->
            button.setOnClickListener {
                radioButtons.forEach { it.isChecked = false } // 先把全部設為 false
                button.isChecked = true // 只讓當前點擊的為 true
                setButtonStatus(button)
            }
        }

        val radioButtons2 = listOf(binding.rbTime2, binding.rbGlobal2)
        radioButtons2.forEach { button2 ->
            button2.setOnClickListener {
                radioButtons2.forEach { it.isChecked = false } // 先把全部設為 false
                button2.isChecked = true // 只讓當前點擊的為 true
                setButtonStatus(button2)
            }
        }

        return binding.root
    }

    private fun observeData() {
        baseHomeModel.mySubHotList.observe(viewLifecycleOwner) { itemList ->
            // 讓 Banner Adapter 更新數據
            binding.homePopularAdsBanner.adapter?.let { adapter ->
                (adapter as HomeAdBannerItemAdapter).setList(itemList, baseHomeModel)
            }
        }

        baseHomeModel.homeBannerList.observe(viewLifecycleOwner) { bannerList ->
            val firstThreeItems = bannerList.take(3)
            val firstThreeItems2 = bannerList.take(5)
            adapter.setList(bannerList) // 更新 RecyclerView 資料
            adapter3.setList(firstThreeItems)
            adapter5.setList(firstThreeItems2)
        }

        baseHomeModel.homeBanner4List.observe(viewLifecycleOwner) { itemList ->
            // 讓 Banner Adapter 更新數據
            binding.homeAdBanner04.adapter?.let { adapter ->
                (adapter as HomeBanne4Adapter).setList(itemList, baseHomeModel)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = HomeBanner2Adapter(this, arrayListOf()) // 初始化时传入空列表
        binding.banner2RecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false) // 設置為橫向
            adapter = this@HomeFragment.adapter
            overScrollMode = View.OVER_SCROLL_NEVER // 防止滾動時出現反彈效果
        }

        adapter3 = HomeBanner3Adapter(this, arrayListOf()) // 初始化时传入空列表
        binding.banner3RecyclerView.apply {
            layoutManager  = GridLayoutManager(requireContext(), 3) // 設置為 3 列
            adapter = this@HomeFragment.adapter3
            overScrollMode = View.OVER_SCROLL_NEVER // 防止滾動時出現反彈效果
        }

        adapter5 = HomeBanner5Adapter(this, arrayListOf()) // 初始化时传入空列表
        binding.banner5RecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@HomeFragment.adapter5
            overScrollMode = View.OVER_SCROLL_NEVER // 防止滾動時出現反彈效果
        }
    }

    // 設定按鈕狀態
    private fun setButtonStatus(selectedButton: RadioButton) {
        when (selectedButton.id) {
            R.id.rb_time -> {
                binding.rbTime.translationZ = 2f
                binding.rbGlobal.translationZ = 1f
                binding.rbShop.translationZ = 0f
                binding.viewBg.translationZ = 1f

                binding.viewBg.setBackgroundResource(R.drawable.bg_home_hour_sale_bottom_active)
                binding.timerSeconds.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_cc1e05))

                baseHomeModel.homeBannerList.value?.let { bannerList ->
                    val firstThreeItems = bannerList.take(3)  // 取前 3 筆資料
                    adapter3.setList(firstThreeItems)
                }
            }
            R.id.rb_global -> {
                binding.rbTime.translationZ = 0f
                binding.rbGlobal.translationZ = 1f
                binding.rbShop.translationZ = 0f
                binding.viewBg.translationZ = 0f

                binding.viewBg.setBackgroundResource(R.drawable.bg_home_global_hoursale_bottom_active)
                binding.timerSeconds.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_fe5a0a))
                // 只顯示第 4 到第 6 筆
                baseHomeModel.homeBannerList.value?.let { bannerList ->
                    val items4to6 = bannerList.drop(3).take(3)  // 取第 4 到第 6 筆資料
                    adapter3.setList(items4to6)
                }
            }
            R.id.rb_shop -> {
                binding.rbTime.translationZ = 1f
                binding.rbGlobal.translationZ = 0f
                binding.rbShop.translationZ = 2f
                binding.viewBg.translationZ = 1f

                binding.viewBg.setBackgroundResource(R.drawable.bg_home_shop_hour_sale_bottom_active)
                binding.timerSeconds.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_007ae6))
                // 只顯示第 6 到第 8 筆
                baseHomeModel.homeBannerList.value?.let { bannerList ->
                    val items6to8 = bannerList.drop(5).take(3)  // 取第 6 到第 8 筆資料
                    adapter3.setList(items6to8)
                }
            }
            R.id.rb_time2 -> {
                binding.rbTime2.translationZ = 2f
                binding.rbGlobal2.translationZ = 1f

                binding.timerSeconds.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_FFB3FF))

                baseHomeModel.homeBannerList.value?.let { bannerList ->
                    val firstThreeItems = bannerList.take(5)  // 取前 3 筆資料
                    adapter5.setList(firstThreeItems)
                }
            }
            R.id.rb_global2 -> {
                binding.rbTime2.translationZ = 0f
                binding.rbGlobal2.translationZ = 1f

                binding.timerSeconds.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_fe5a0a))
                // 只顯示第 4 到第 6 筆
                baseHomeModel.homeBannerList.value?.let { bannerList ->
                    val items6to8 = bannerList.drop(4).take(4)  // 取第 6 到第 8 筆資料
                    adapter5.setList(items6to8)
                }
            }
        }
    }
}