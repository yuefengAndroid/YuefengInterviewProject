package com.example.yuefenginterviewproject.ui.bestsellers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.BestsellersViewModel
import com.example.yuefenginterviewproject.databinding.FragmentBestsellersBinding
import com.example.yuefenginterviewproject.ui.cart.CartActivity
import com.example.yuefenginterviewproject.ui.home.BaseHomePagerAdapter
import com.example.yuefenginterviewproject.ui.messages.MessagesActivity
import com.example.yuefenginterviewproject.ui.searchview.SearchViewFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class bestsellersFragment : Fragment() {
    private lateinit var binding: FragmentBestsellersBinding
    private val baseHomeModel: BestsellersViewModel by lazy {
        ViewModelProvider(this).get(BestsellersViewModel::class.java) // 初始化 ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBestsellersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // 設定 DataBinding 的生命周期擁有者
        binding.baseModel = baseHomeModel // 將 ViewModel 綁定到 Binding

        observeViewModel()

        // 设置点击事件
        binding.strdemo.setOnClickListener {
            showSearchViewFragment()
        }
        binding.searchText.setOnClickListener {
            showSearchViewFragment()
        }
        binding.iconSearch01.setOnClickListener {
            showSearchViewFragment()
        }

        binding.iconMenu.setOnClickListener {
            hideSearchViewFragment()
        }

        binding.iconCart.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java)
            startActivity(intent)
        }

        binding.iconInformation.setOnClickListener {
            val intent = Intent(requireContext(), MessagesActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun observeViewModel() {
        // 觀察 ViewModel 的 LiveData
        baseHomeModel.fragmentListLiveData.observe(viewLifecycleOwner) { fragments ->
            baseHomeModel.tabTitlesLiveData.observe(viewLifecycleOwner) { tabTitles ->
                setupTabLayoutAndViewPager(fragments, tabTitles)
            }
        }
    }

    private fun setupTabLayoutAndViewPager(fragments: List<Fragment>, tabTitles: List<String>) {
        // 設置 ViewPager2 適配器
        val adapter = BaseHomePagerAdapter(this, fragments)
        binding.homeViewpager2.adapter = adapter
        binding.homeViewpager2.isUserInputEnabled = false // 禁用滑动

        // 使用 TabLayoutMediator 綁定 TabLayout 和 ViewPager2
        TabLayoutMediator(binding.homeTabLayout2, binding.homeViewpager2) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        // 監聽 Tab 點擊事件，使用 setCurrentItem(position, false) 關閉切換動畫
        binding.homeTabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    binding.homeViewpager2.setCurrentItem(it.position, false) // 這裡關閉切換動畫
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // 自定義 TabLayout 選中顏色和下底線顏色 (如果在XML有設定,則以下設定顏色程式可以省略)
        binding.homeTabLayout2.setSelectedTabIndicatorColor(resources.getColor(R.color.red, null))
        binding.homeTabLayout2.setTabTextColors(
            resources.getColor(R.color.black, null),
            resources.getColor(R.color.red, null)
        )
        binding.fragmentContainer.animate().alpha(1f).setDuration(300).start()
    }

    private fun showSearchViewFragment() {
        val fragmentManager = childFragmentManager
        val searchViewFragmentTag = "SearchViewFragment"
        // 替換 iconMenu 的圖標為 icon_back_ffffff_24
        binding.iconMenu.setImageResource(R.drawable.icon_back_ffffff_24)
        // 隱藏 iconSearch01 和 strdemo
        binding.iconSearch01.visibility = View.GONE
        binding.strdemo.visibility = View.GONE

        // 顯示 inputSearch
        binding.inputSearch.visibility = View.VISIBLE
        binding.inputSearch.text = null
        binding.inputSearch.requestFocus()

        // 開啟鍵盤
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.inputSearch, InputMethodManager.SHOW_IMPLICIT)

        // 確認是否已經顯示 CartFragment，避免重複添加
        if (fragmentManager.findFragmentByTag(searchViewFragmentTag) == null) {
            val searchViewFragment = SearchViewFragment()
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, searchViewFragment, searchViewFragmentTag) // 使用 replace 替換內容
                .commitAllowingStateLoss()

            // 設置 FrameLayout 可見
            binding.fragmentContainer.visibility = View.VISIBLE
            binding.fragmentContainer.animate()
                .alpha(1f)
                .setDuration(300)
                .start()
            binding.homeTabLayout2.visibility = View.INVISIBLE
            binding.homeViewpager2.visibility = View.INVISIBLE

        }
    }

    private fun hideSearchViewFragment() {
        val fragmentManager = childFragmentManager
        val searchViewFragmentTag = "SearchViewFragment"
        // 替換 iconMenu 的圖標為 icon_menu_24
        binding.iconMenu.setImageResource(R.drawable.icon_menu_24)

        // 顯示 iconSearch01 和 strdemo
        binding.iconSearch01.visibility = View.VISIBLE
        binding.strdemo.visibility = View.VISIBLE

        // 關閉輸入鍵盤
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.inputSearch.windowToken, 0)

        // 隱藏 inputSearch
        binding.inputSearch.visibility = View.GONE
        // 找到 CartFragment 並移除
        val cartFragment = fragmentManager.findFragmentByTag(searchViewFragmentTag)
        if (cartFragment != null) {
            fragmentManager.beginTransaction()
                .remove(cartFragment)
                .commitAllowingStateLoss()

            // 設置 FrameLayout 隱藏
            binding.fragmentContainer.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    binding.fragmentContainer.visibility = View.GONE
                    binding.homeTabLayout2.visibility = View.VISIBLE
                    binding.homeViewpager2.visibility = View.VISIBLE
                }
                .start()
        }
    }

}