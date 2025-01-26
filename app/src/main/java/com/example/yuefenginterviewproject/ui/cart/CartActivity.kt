package com.example.yuefenginterviewproject.ui.cart

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)

        // 设置返回按钮的点击事件
        binding.backText.setOnClickListener {
            val intent = Intent().apply {
                putExtra("GO_TO_HOME_TAB", true) // 向 MainHomeActivity 传递数据
            }
            setResult(Activity.RESULT_OK, intent) // 设置结果
            finish() // 结束当前 Activity
        }
    }
}