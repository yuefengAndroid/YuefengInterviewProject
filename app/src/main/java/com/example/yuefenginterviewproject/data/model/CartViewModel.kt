package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.entity.CartItemsEntity
import com.example.yuefenginterviewproject.data.repository.CartRepository

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val cartRepository: CartRepository by lazy { CartRepository() }
    private val _myItemList = MutableLiveData<List<CartItemsEntity>>() // 使用不可變 LiveData
    val myItemList: LiveData<List<CartItemsEntity>> get() = _myItemList


    init {
        loadEventData()
    }

    private fun loadEventData() {
        cartRepository.getCartItemsData(object : CartRepository.OnProductsFinish {
            override fun onFinish(itemsEntity: MutableList<CartItemsEntity>) {
                _myItemList.postValue(itemsEntity)  // 更新 LiveData，通知 UI
            }
        })
    }
}