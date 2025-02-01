package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.repository.TvHotRepository
import com.example.yuefenginterviewproject.ui.tvhot.tvsubview.TvSub01Fragment
import com.example.yuefenginterviewproject.ui.tvhot.tvsubview.TvSub02Fragment
import com.example.yuefenginterviewproject.ui.tvhot.tvsubview.TvSub03Fragment

class TvHotViewModel(application: Application) : AndroidViewModel(application) {

    private val homeRepository: TvHotRepository by lazy { TvHotRepository() }

    // LiveData 用於數據綁定
    val fragmentListLiveData4 = MutableLiveData<List<Fragment>>()
    val tabTitlesLiveData4 = MutableLiveData<List<String>>()

    init {

        // 初始化 Fragment 列表和 Tab 標題
        val fragments = listOf(
            TvSub01Fragment(),
            TvSub02Fragment(),
            TvSub03Fragment(),
            TvSub01Fragment(),
            TvSub02Fragment()
        )// 模擬 5 個 HomeFragment

        val tabTitles = homeRepository.getTabTitles()

        fragmentListLiveData4.value = fragments
        tabTitlesLiveData4.value = tabTitles

    }
}
