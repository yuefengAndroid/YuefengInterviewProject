package com.example.yuefenginterviewproject.ui.messages

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.yuefenginterviewproject.R
import com.example.yuefenginterviewproject.databinding.ActivityMessagesBinding

class MessagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_messages)
        binding.iconBack.setOnClickListener {
            val intent = Intent().apply {
                putExtra("GO_TO_HOME_TAB", true) // 向 MainHomeActivity 传递数据
            }
            setResult(Activity.RESULT_OK, intent) // 设置结果
            finish() // 结束当前 Activity
        }
    }
}