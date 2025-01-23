package com.example.yuefenginterviewproject.data.model

import android.app.Application
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.entity.NavbarEntity
import com.example.yuefenginterviewproject.data.repository.MemberRepository
import com.example.yuefenginterviewproject.ui.member.MemberNavbarItemAdapter
import com.example.yuefenginterviewproject.ui.member.MemberTicketsItemAdapter

class MemberViewModel(application: Application) :
    AndroidViewModel(application) {
    private lateinit var memberRepository: MemberRepository
    val navbarList = MutableLiveData<MutableList<NavbarEntity>>()

    val myTicketsList = MutableLiveData<MutableList<NavbarEntity>>()

    private var lastClickTime: Long = 0

    fun setRepository(repository: MemberRepository) {
        this.memberRepository = repository

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
    }

    fun onNavBarClick(navbarEntity: NavbarEntity) {
        val curTime = System.currentTimeMillis()
        //防止連點判斷
        if (curTime - lastClickTime > 1000) {
            lastClickTime = curTime
        }
    }

    companion object {
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