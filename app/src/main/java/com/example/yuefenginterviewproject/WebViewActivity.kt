package com.example.yuefenginterviewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yuefenginterviewproject.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    lateinit var urlLoad: String
    lateinit var webViewTitle: String
    lateinit var binding: ActivityWebViewBinding
   // lateinit var myApplication: BaseMyProjectApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
    }
}