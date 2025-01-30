package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.entity.EventEntity
import com.example.yuefenginterviewproject.data.repository.MessageEventRepository

class MessageEventViewModel (application: Application) : AndroidViewModel(application) {

    private val eventRepository: MessageEventRepository by lazy { MessageEventRepository() }
    val myEventList = MutableLiveData<MutableList<EventEntity>>()

    init {
        loadEventData()
    }

    private fun loadEventData() {
        eventRepository.getEventsData(object : MessageEventRepository.OnProductsFinish {
            override fun onFinish(eventEntity: MutableList<EventEntity>) {
                myEventList.postValue(eventEntity) // 更新 LiveData，通知 UI
            }
        })
    }
}