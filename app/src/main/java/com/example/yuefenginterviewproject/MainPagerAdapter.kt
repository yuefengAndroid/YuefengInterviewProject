package com.example.yuefenginterviewproject

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.yuefenginterviewproject.ui.bestsellers.bestsellersFragment
import com.example.yuefenginterviewproject.ui.cart.CartFragment
import com.example.yuefenginterviewproject.ui.home.HomeFragment
import com.example.yuefenginterviewproject.ui.member.MemberFragment
import com.example.yuefenginterviewproject.ui.popularactivities.popularactivitiesFragment
import com.example.yuefenginterviewproject.ui.tvhot.TvHotFragment

class MainPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 6 // 共6個頁面

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> bestsellersFragment()
            2 -> popularactivitiesFragment()
            3 -> TvHotFragment()
            4 -> CartFragment()
            5 -> MemberFragment() // 新增第六個頁面
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }
}

