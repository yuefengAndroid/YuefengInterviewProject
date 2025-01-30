package com.example.yuefenginterviewproject.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.yuefenginterviewproject.data.entity.SearchProduct
import com.example.yuefenginterviewproject.data.repository.SearchRepository

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val searchRepository: SearchRepository by lazy { SearchRepository() }
    val myProductsList = MutableLiveData<MutableList<SearchProduct>>()
    val searchListsLiveData = MutableLiveData<Pair<List<String>, List<String>>>()

    init {
        searchListsLiveData.value = searchRepository.getTagLists()

        searchRepository.getProductsData(object : SearchRepository.OnProductsFinish {
            override fun onFinish(searchEntity: MutableList<SearchProduct>) {
                myProductsList.postValue(searchEntity)
            }
        })
    }
}