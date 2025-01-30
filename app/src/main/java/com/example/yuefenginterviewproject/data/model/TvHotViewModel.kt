package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.repository.TvHotRepository
import com.example.yuefenginterviewproject.ui.cart.CartFragment
import com.example.yuefenginterviewproject.ui.home.BaseHomePagerAdapter
import com.example.yuefenginterviewproject.ui.home.tabfragment.HomeFragment

class TvHotViewModel(application: Application) : AndroidViewModel(application) {

    private val homeRepository: TvHotRepository by lazy { TvHotRepository() }

    // LiveData 用於數據綁定
    val fragmentListLiveData4 = MutableLiveData<List<Fragment>>()
    val tabTitlesLiveData4 = MutableLiveData<List<String>>()

    // 適配器 LiveData
    val adapterLiveData = MutableLiveData<BaseHomePagerAdapter>()

    init {

        // 初始化 Fragment 列表和 Tab 標題
        val fragments = listOf(
            HomeFragment(),
            CartFragment(),
            HomeFragment(),
            HomeFragment(),
            HomeFragment()
        )// 模擬 5 個 HomeFragment

        val tabTitles = homeRepository.getTabTitles()

        fragmentListLiveData4.value = fragments
        tabTitlesLiveData4.value = tabTitles

    }
}
