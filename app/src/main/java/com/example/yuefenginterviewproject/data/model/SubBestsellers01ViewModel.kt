package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.entity.BestsellersEntity
import com.example.yuefenginterviewproject.data.repository.SubBestsellers01Repository

class SubBestsellers01ViewModel (application: Application) : AndroidViewModel(application) {

    private val subBestsellers01Repository: SubBestsellers01Repository by lazy { SubBestsellers01Repository() }
    private val _mySubBestsellers01List = MutableLiveData<MutableList<BestsellersEntity>>()
    val mySubBestsellers01List: LiveData<MutableList<BestsellersEntity>> get() = _mySubBestsellers01List

    private val _mySubBestsellers02List = MutableLiveData<MutableList<BestsellersEntity>>()
    val mySubBestsellers02List: LiveData<MutableList<BestsellersEntity>> get() = _mySubBestsellers02List

    init {
        loadBestsellersData()
        loadBestsellersData2()
    }

    private fun loadBestsellersData() {
        subBestsellers01Repository.getSubBestsellers01Data(object : SubBestsellers01Repository.OnProductsFinish {
            override fun onFinish(bestsellersEntity: MutableList<BestsellersEntity>) {
                _mySubBestsellers01List.postValue(bestsellersEntity)
            }
        })
    }

    private fun loadBestsellersData2() {
        subBestsellers01Repository.getSubBestsellers02Data(object : SubBestsellers01Repository.OnProductsFinish {
            override fun onFinish(bestsellersEntity: MutableList<BestsellersEntity>) {
                _mySubBestsellers02List.postValue(bestsellersEntity)
            }
        })
    }

}