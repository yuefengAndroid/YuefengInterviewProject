package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.entity.SubHotEntity
import com.example.yuefenginterviewproject.data.repository.SubEvent01Repository

class SubEvent02ViewModel(application: Application) : AndroidViewModel(application) {
    private val subHotRepository: SubEvent01Repository by lazy { SubEvent01Repository() }
    private val _mySubHotList = MutableLiveData<MutableList<SubHotEntity>>()
    val mySubHotList: LiveData<MutableList<SubHotEntity>> get() = _mySubHotList

    init {
        loadSubHotData()

    }
    private fun loadSubHotData() {
        subHotRepository.getSubHot2Data(object : SubEvent01Repository.OnSubHotFinish {
            override fun onFinish(subHotEntity: MutableList<SubHotEntity>) {
                _mySubHotList.postValue(subHotEntity)
            }
        })
    }
}