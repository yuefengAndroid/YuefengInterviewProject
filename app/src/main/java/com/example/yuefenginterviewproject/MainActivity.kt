package com.example.yuefenginterviewproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.yuefenginterviewproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 使用 Handler 進行延遲
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainHomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // 2000 毫秒（2 秒）

    }

}