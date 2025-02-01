package com.example.yuefenginterviewproject.data.model

import android.app.Application
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.example.yuefenginterviewproject.BaseScaleInTransformer
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.SubHotEntity
import com.example.yuefenginterviewproject.data.repository.HomeRepository
import com.example.yuefenginterviewproject.ui.home.tabfragment.HomeAdBannerItemAdapter
import com.to.aboomy.pager2banner.Banner
import com.to.aboomy.pager2banner.IndicatorView

class HomeViewModel(application: Application) :
    AndroidViewModel(application) {

    private val homeRepository: HomeRepository by lazy { HomeRepository() }
    private val _mySubHotList = MutableLiveData<MutableList<SubHotEntity>>()
    val mySubHotList: LiveData<MutableList<SubHotEntity>> get() = _mySubHotList

    init {


        homeRepository.getSubHotData(object : HomeRepository.OnSubHotFinish {
            override fun onFinish(subHotEntity: MutableList<SubHotEntity>) {
                _mySubHotList.postValue(subHotEntity)
            }
        })
    }

    //首頁的廣告輪播點擊事件
    fun onHomeBannerClick(subHotEntity: SubHotEntity) {

    }

    companion object {
        @BindingAdapter("home_banner_model", "home_banner_adapter", "indicatorView")
        @JvmStatic
        fun setAdBanner(
            bannerLayout: RelativeLayout?,
            homeViewModel: HomeViewModel,
            storeCouponEntityList: MutableList<SubHotEntity>?,
            indicator: View
        ) {
            val banner = bannerLayout as Banner
            var adapter = banner.adapter as? HomeAdBannerItemAdapter

            if (adapter == null) {
                adapter = HomeAdBannerItemAdapter()
                banner.setAutoTurningTime(2000)
                    .setIndicator(
                        (indicator.findViewById<IndicatorView>(R.id.indicator2))
                            .setIndicatorColor(
                                ContextCompat.getColor(
                                    bannerLayout.context,
                                    R.color.gray_cccccc
                                )
                            )
                            .setIndicatorSelectorColor(Color.GRAY), false
                    )
                banner.setPageMargin(40, 40).addPageTransformer(BaseScaleInTransformer())
                banner.adapter = adapter
            }

            if (storeCouponEntityList != null) {
                adapter.setList(storeCouponEntityList, homeViewModel)
            }
        }

        @BindingAdapter("load_banner_image")
        @JvmStatic
        fun loadImageByGlide(imageView: ImageView, url: String) {
            Glide.with(imageView.context.applicationContext)
                .asBitmap()
                .transition(BitmapTransitionOptions.withCrossFade())
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(imageView)
        }

    }

}