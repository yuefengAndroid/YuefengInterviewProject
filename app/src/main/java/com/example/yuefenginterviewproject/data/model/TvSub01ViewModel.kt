package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.entity.ProductsEntity
import com.example.yuefenginterviewproject.data.repository.TvSubRepository

class TvSub01ViewModel (application: Application) : AndroidViewModel(application) {
    private val tvSubRepository: TvSubRepository by lazy { TvSubRepository() }
    private val _myTvSubList = MutableLiveData<MutableList<ProductsEntity>>()
    val myTvSubList: LiveData<MutableList<ProductsEntity>> get() = _myTvSubList

    init {
        tvSubRepository.getProductsData(object : TvSubRepository.OnProductsFinish {
            override fun onFinish(productsEntity: MutableList<ProductsEntity>) {
                _myTvSubList.postValue(productsEntity)
            }

        })
    }

}