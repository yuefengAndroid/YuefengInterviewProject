package com.example.yuefenginterviewproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseFragment<T: ViewDataBinding>: Fragment() {
    lateinit var binding: T
    abstract val resId: Int
    lateinit var activity: AppCompatActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, resId, container, false)
        binding.lifecycleOwner = this
        initAfterBinding()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    open fun initAfterBinding(){}

    fun switchTab(viewPager2: ViewPager2, tabPosition: Int){
        lifecycleScope.launch {
            delay(100)
            viewPager2.setCurrentItem(tabPosition, false)
        }
    }

    fun switchSpinner(spinner: Spinner, spinnerPosition: Int){
        lifecycleScope.launch {
            delay(100)
            spinner.setSelection(spinnerPosition)
        }
    }

    class BackgroundImage {
        var ImageUrl: String? = null
        @DrawableRes
        var DrawableId: Int? = null
        var BitmapRatio: Float? = null
        var Index: Array<String>? = null

        override fun toString(): String {
            return "ClassPojo [ImageUrl = $ImageUrl, DrawableId = $DrawableId, BitmapRatio = $BitmapRatio, Index = $Index]"
        }
    }

}