package com.example.yuefenginterviewproject.data.model

import android.app.Application
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.example.yuefenginterviewproject.BaseMyProjectApplication
import com.example.yuefenginterviewproject.BaseScaleInTransformer
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.NavbarEntity
import com.example.yuefenginterviewproject.data.entity.ProductsEntity
import com.example.yuefenginterviewproject.data.entity.StoreCouponEntity
import com.example.yuefenginterviewproject.data.repository.MemberRepository
import com.example.yuefenginterviewproject.ui.member.MemberAdBannerItemAdapter
import com.example.yuefenginterviewproject.ui.member.MemberNavbarItemAdapter
import com.example.yuefenginterviewproject.ui.member.MemberProductItemAdapter
import com.example.yuefenginterviewproject.ui.member.MemberTicketsItemAdapter
import com.example.yuefenginterviewproject.ui.member.MemberTreasureBoxAdapter
import com.to.aboomy.pager2banner.Banner
import com.to.aboomy.pager2banner.IndicatorView


class MemberViewModel(application: Application) :
    AndroidViewModel(application) {

    private val memberRepository: MemberRepository by lazy { MemberRepository() }

    val adBannerList = MutableLiveData<MutableList<StoreCouponEntity>>()

    val navbarList = MutableLiveData<MutableList<NavbarEntity>>()

    val myTicketsList = MutableLiveData<MutableList<NavbarEntity>>()

    val myTreasureBoxList = MutableLiveData<MutableList<NavbarEntity>>()

    val myOthersList = MutableLiveData<MutableList<NavbarEntity>>()

    val myHelpList = MutableLiveData<MutableList<NavbarEntity>>()

    val myProductsList = MutableLiveData<MutableList<ProductsEntity>>()

    private var lastClickTime: Long = 0
    init {

        memberRepository.getAdBannerData(object : MemberRepository.OnActivityBannerFinish {
            override fun onFinish(storeCouponEntity: MutableList<StoreCouponEntity>) {
                adBannerList.postValue(storeCouponEntity)
            }

        })

        memberRepository.getMyOrderData(object : MemberRepository.OnNavbarFinish {
            override fun onFinish(navbarEntity: MutableList<NavbarEntity>) {
                navbarList.postValue(navbarEntity)
            }

        })

        memberRepository.getMyTicketsData(object : MemberRepository.OnNavbarFinish {
            override fun onFinish(navbarEntity: MutableList<NavbarEntity>) {
                myTicketsList.postValue(navbarEntity)
            }

        })

        memberRepository.getTreasureBoxData(object : MemberRepository.OnNavbarFinish {
            override fun onFinish(navbarEntity: MutableList<NavbarEntity>) {
                myTreasureBoxList.postValue(navbarEntity)
            }

        })

        memberRepository.getOthersData(object : MemberRepository.OnNavbarFinish {
            override fun onFinish(navbarEntity: MutableList<NavbarEntity>) {
                myOthersList.postValue(navbarEntity)
            }

        })

        memberRepository.getHelpBoxData(object : MemberRepository.OnNavbarFinish {
            override fun onFinish(navbarEntity: MutableList<NavbarEntity>) {
                myHelpList.postValue(navbarEntity)
            }

        })

        memberRepository.getProductsData(object : MemberRepository.OnProductsFinish {
            override fun onFinish(productsEntity: MutableList<ProductsEntity>) {
                myProductsList.postValue(productsEntity)
            }

        })
    }
    fun setRepository(repository: MemberRepository) {

    }

    //首頁的廣告輪播點擊事件
    fun onBannerClick(storeCouponEntity: StoreCouponEntity) {
        //2022/02/18 如果要使用getApplication<BaseCoinApplication> 要記得 AndroidManifest.xml 裡要記得宣告name
        //android:name=".BaseCoinApplication"
        for (store in adBannerList.value!!) {
            if (store.store_id == storeCouponEntity.store_id) {
                storeCouponEntity.store_name = store.store_name
                storeCouponEntity.store_logo = store.store_logo
                storeCouponEntity.address = store.address
                break
            }
        }

        storeCouponEntity.webviewUrl?.let {
            getApplication<BaseMyProjectApplication>().openWebView(
                it,
                storeCouponEntity.store_name.toString()
            )
        }
    }

    fun onNavBarClick(navbarEntity: NavbarEntity) {
        val curTime = System.currentTimeMillis()
        //防止連點判斷
        if (curTime - lastClickTime > 1000) {
            lastClickTime = curTime
        }
    }

    fun onProductClick(productsEntity: ProductsEntity) {
        val curTime = System.currentTimeMillis()
        //防止連點判斷
        if (curTime - lastClickTime > 1000) {
            lastClickTime = curTime
        }
    }

    companion object {

        //第一區塊-廣告輪播
        @BindingAdapter("member_banner_model", "member_banner_adapter", "indicatorView")
        @JvmStatic
        fun setAdBanner(
            bannerLayout: RelativeLayout?,
            homeViewModel: MemberViewModel,
            storeCouponEntityList: MutableList<StoreCouponEntity>?,
            indicator: View
        ) {
            val banner = bannerLayout as Banner
            var adapter = banner.adapter as? MemberAdBannerItemAdapter

            if (adapter == null) {
                adapter = MemberAdBannerItemAdapter()
                banner.setAutoTurningTime(2000)
                    .setIndicator(
                        (indicator.findViewById<IndicatorView>(R.id.indicator2))
                            .setIndicatorColor(ContextCompat.getColor(bannerLayout.context, R.color.custom_pink))
                            .setIndicatorSelectorColor(Color.RED), false
                    )
                banner.setPageMargin(0, 0).addPageTransformer(BaseScaleInTransformer())
                banner.adapter = adapter
            }

            if (storeCouponEntityList != null) {
                adapter.setList(storeCouponEntityList, homeViewModel)
            }
        }

        //我的訂單
        @BindingAdapter("member_order_adapter", "member_order_recyclerview")
        @JvmStatic
        fun setMyOrderRecyclerViewList(
            recyclerView: RecyclerView, navbarEntity: MutableList<NavbarEntity>?,
            memberViewModel: MemberViewModel
        ) {
            var adapter = recyclerView.adapter as? MemberNavbarItemAdapter
            if (adapter == null) {
                adapter = MemberNavbarItemAdapter()
                recyclerView.adapter = adapter
            }

            val gridLayoutManager = GridLayoutManager(recyclerView.context, 1)
            gridLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            recyclerView.layoutManager = gridLayoutManager

            recyclerView.itemAnimator = DefaultItemAnimator()

            if (navbarEntity == null) {
                return
            }
            adapter.setList(navbarEntity, memberViewModel)
        }

        //我的券匣
        @BindingAdapter("member_tickets_adapter", "member_tickets_recyclerview")
        @JvmStatic
        fun setMyTicketsRecyclerViewList(
            recyclerView: RecyclerView, navbarEntity: MutableList<NavbarEntity>?,
            memberViewModel: MemberViewModel
        ) {
            var adapter = recyclerView.adapter as? MemberNavbarItemAdapter
            if (adapter == null) {
                adapter = MemberNavbarItemAdapter()
                recyclerView.adapter = adapter
            }

            val gridLayoutManager = GridLayoutManager(recyclerView.context, 2)
            gridLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            var rowSpacing = 0
            if (navbarEntity != null && navbarEntity.size > 0) {
                val dpWidth = recyclerView.context.resources.displayMetrics.widthPixels
                val density = recyclerView.context.resources.displayMetrics.density
                rowSpacing = when (navbarEntity.size) {
                    1, 2 -> {
                        ((dpWidth - 82 * density) / 2).toInt()
                    }

                    3, 4 -> {
                        ((dpWidth / 2 - 82 * density) / 2).toInt()
                    }

                    5, 6 -> {
                        ((dpWidth / 3 - 82 * density) / 2).toInt()
                    }

                    else -> {
                        ((dpWidth / 4 - 82 * density) / 2).toInt()
                    }
                }
            }
            recyclerView.layoutManager = gridLayoutManager
            recyclerView.addItemDecoration(MemberTicketsItemAdapter.HomeNavbarItemDecoration(rowSpacing))
            recyclerView.itemAnimator = DefaultItemAnimator()

            if (navbarEntity == null) {
                return
            }
            adapter.setList(navbarEntity, memberViewModel)
        }

        //優惠百寶箱
        @BindingAdapter("member_TreasureBox_adapter", "member_TreasureBox_recyclerview")
        @JvmStatic
        fun setMyTreasureBoxRecyclerViewList(
            recyclerView: RecyclerView, navbarEntity: MutableList<NavbarEntity>?,
            memberViewModel: MemberViewModel
        ) {
            var adapter = recyclerView.adapter as? MemberTreasureBoxAdapter
            if (adapter == null) {
                adapter = MemberTreasureBoxAdapter()
                recyclerView.adapter = adapter
            }

            val gridLayoutManager = GridLayoutManager(recyclerView.context, 4)
            gridLayoutManager.orientation = RecyclerView.VERTICAL
            // 自訂 SpanSizeLookup，確保每個項目佔用 1 個 span
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1 // 每個項目佔用 1 個 span
                }
            }

            recyclerView.layoutManager = gridLayoutManager

            // 計算列間距並應用 ItemDecoration
            val density = recyclerView.context.resources.displayMetrics.density
            val columnSpacing = (8 * density).toInt() // 假設列間距為 8dp
            recyclerView.addItemDecoration(MemberTreasureBoxAdapter.HomeNavbarItemDecoration(columnSpacing))
            recyclerView.itemAnimator = DefaultItemAnimator()

            if (navbarEntity == null) {
                return
            }
            adapter.setList(navbarEntity, memberViewModel)
        }

        //專屬推薦
        @BindingAdapter("member_Products_adapter", "member_Products_recyclerview")
        @JvmStatic
        fun setMyProductsRecyclerViewList(
            recyclerView: RecyclerView, productsEntity: MutableList<ProductsEntity>?,
            memberViewModel: MemberViewModel
        ) {
            var adapter = recyclerView.adapter as? MemberProductItemAdapter
            if (adapter == null) {
                adapter = MemberProductItemAdapter()
                recyclerView.adapter = adapter
            }

            val gridLayoutManager = GridLayoutManager(recyclerView.context, 2)
            gridLayoutManager.orientation = RecyclerView.VERTICAL
            // 自訂 SpanSizeLookup，確保每個項目佔用 1 個 span
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1 // 每個項目佔用 1 個 span
                }
            }

            recyclerView.layoutManager = gridLayoutManager

            // 計算列間距並應用 ItemDecoration
            val density = recyclerView.context.resources.displayMetrics.density
            val columnSpacing = (4 * density).toInt() // 假設列間距為 8dp
            recyclerView.addItemDecoration(MemberProductItemAdapter.HomeNavbarItemDecoration(columnSpacing))
            recyclerView.itemAnimator = DefaultItemAnimator()

            if (productsEntity == null) {
                return
            }
            adapter.setList(productsEntity, memberViewModel)
        }

        //ImageView的共用設定
        @BindingAdapter("load_image")
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