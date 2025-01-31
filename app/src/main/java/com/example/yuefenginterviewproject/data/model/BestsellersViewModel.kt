package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.repository.BestsellersRepository
import com.example.yuefenginterviewproject.ui.bestsellers.subview3.SubBestsellers01Fragment
import com.example.yuefenginterviewproject.ui.bestsellers.subview3.SubBestsellers02Fragment
import com.example.yuefenginterviewproject.ui.cart.CartFragment
import com.example.yuefenginterviewproject.ui.home.BaseHomePagerAdapter
import com.example.yuefenginterviewproject.ui.home.tabfragment.HomeFragment

class BestsellersViewModel(application: Application) : AndroidViewModel(application) {

    private val homeRepository: BestsellersRepository by lazy { BestsellersRepository() }

    // LiveData 用於數據綁定
    val fragmentListLiveData = MutableLiveData<List<Fragment>>()
    val tabTitlesLiveData = MutableLiveData<List<String>>()

    init {

        // 初始化 Fragment 列表和 Tab 標題
        val fragments = listOf(
            SubBestsellers01Fragment(),
            SubBestsellers02Fragment(),
            SubBestsellers01Fragment(),
            SubBestsellers02Fragment(),
            SubBestsellers01Fragment(),
            SubBestsellers02Fragment(),
            SubBestsellers01Fragment(),
            SubBestsellers02Fragment(),
            SubBestsellers01Fragment(),
            SubBestsellers02Fragment()
        )// 模擬 10 個 HomeFragment

        val tabTitles = homeRepository.getTabTitles()

        fragmentListLiveData.value = fragments
        tabTitlesLiveData.value = tabTitles

    }
}