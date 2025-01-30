package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.repository.MessagesRepository
import com.example.yuefenginterviewproject.ui.messages.subview.MessageEventFragment
import com.example.yuefenginterviewproject.ui.messages.subview.MessageNotifyFragment

class MessagesViewModel (application: Application) : AndroidViewModel(application) {

    private val messagesRepository: MessagesRepository by lazy { MessagesRepository() }
    // LiveData 用於數據綁定
    val fragmentListLiveData = MutableLiveData<List<Fragment>>()
    val tabTitlesLiveData = MutableLiveData<List<String>>()

    init {
        // 初始化 Fragment 列表和 Tab 標題
        val fragments = listOf(
            MessageEventFragment(),
            MessageNotifyFragment()
        )// 模擬 2 個 HomeFragment

        val tabTitles = messagesRepository.getTabTitles()

        fragmentListLiveData.value = fragments
        tabTitlesLiveData.value = tabTitles
    }
}