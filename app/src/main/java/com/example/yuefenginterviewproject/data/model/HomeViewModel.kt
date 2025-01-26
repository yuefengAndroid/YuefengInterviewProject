package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.repository.HomeRepository

class HomeViewModel (application: Application) :
    AndroidViewModel(application) {

    private val homeRepository: HomeRepository by lazy { HomeRepository() }

    // LiveData 用於數據綁定
    val someLiveData = MutableLiveData<String>()


    init {
         someLiveData.value = homeRepository.getData()
    }


}