package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.repository.popularactivitiesRepository
import com.example.yuefenginterviewproject.ui.cart.CartFragment
import com.example.yuefenginterviewproject.ui.home.BaseHomePagerAdapter
import com.example.yuefenginterviewproject.ui.home.tabfragment.HomeFragment

class PopularactivitiesViewModel(application: Application) : AndroidViewModel(application) {

    private val homeRepository: popularactivitiesRepository by lazy { popularactivitiesRepository() }

    // LiveData 用於數據綁定
    val fragmentListLiveData3 = MutableLiveData<List<Fragment>>()
    val tabTitlesLiveData3 = MutableLiveData<List<String>>()

    // 適配器 LiveData
    val adapterLiveData = MutableLiveData<BaseHomePagerAdapter>()

    init {

        // 初始化 Fragment 列表和 Tab 標題
        val fragments = listOf(
            HomeFragment(),
            CartFragment(),
            HomeFragment(),
            HomeFragment(),
            HomeFragment(),
            HomeFragment(),
            HomeFragment(),
            HomeFragment(),
            HomeFragment(),
            HomeFragment()
        )// 模擬 10 個 HomeFragment

        val tabTitles = homeRepository.getTabTitles()

        fragmentListLiveData3.value = fragments
        tabTitlesLiveData3.value = tabTitles

    }
}