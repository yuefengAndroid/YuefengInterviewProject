package com.example.yuefenginterviewproject.data.model

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainHomeViewModel(application: Application) : AndroidViewModel(application) {
    val navId = MutableLiveData<Int>()
    val navBundle =  MutableLiveData<Bundle>()


    fun openNavView(id: Int, bundle: Bundle = Bundle()) {

        navBundle.value = bundle

        //Let navId to assign last because it need to carry bundle item first in MainActivity openNavFrag().
        navId.value = id
    }

}