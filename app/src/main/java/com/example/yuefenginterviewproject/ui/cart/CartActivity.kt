package com.example.yuefenginterviewproject.ui.cart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.data.model.CartViewModel
import com.example.yuefenginterviewproject.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private val cartViewModel: CartViewModel by lazy {
        ViewModelProvider(this)[CartViewModel::class.java] // 初始化 ViewModel
    }
    private lateinit var cartAdapter: CartItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)
        binding.cartViewModel = cartViewModel
        binding.lifecycleOwner = this // 確保 DataBinding 可以監聽 LiveData

        // 初始化 RecyclerView
        setupRecyclerView()

        // 監聽 ViewModel LiveData 來更新 RecyclerView
        cartViewModel.myItemList.observe(this) { itemList ->
            cartAdapter.updateList(itemList)
        }

        binding.iconBack.setOnClickListener {
            val intent = Intent().apply {
                putExtra("GO_TO_HOME_TAB", true) // 向 MainHomeActivity 传递数据
            }
            setResult(Activity.RESULT_OK, intent) // 设置结果
            finish() // 结束当前 Activity
        }


    }

    private fun setupRecyclerView() {
        cartAdapter = CartItemListAdapter(this, arrayListOf()) // 初始化時給空列表
        binding.cartItemView.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }
    }
}