package com.example.yuefenginterviewproject

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker

class BaseMyProjectApplication : Application() {
    lateinit var sAnalytics: GoogleAnalytics
    private var sTracker: Tracker? = null
    private lateinit var builder: HitBuilders.EventBuilder

    override fun onCreate() {
        super.onCreate()

    }

    @Synchronized
    fun getDefaultTracker() : Tracker? {
        if (sTracker == null)
            sTracker = sAnalytics.newTracker("test0427")
        return sTracker
    }

    fun openActivity(clazz: Class<*>?, bundle: Bundle = Bundle()){
        val intent = Intent(this, clazz!!)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("bundle", bundle)
        startActivity(intent)
    }

    fun sendUrlSchemeBroadcast(urlSchemeCode: Int, bundle: Bundle = Bundle()){
        val intent = Intent("UrlScheme")
        intent.putExtra("bundle", bundle)
        intent.putExtra("url", "ehscoin://open?pgid=${urlSchemeCode}")
        sendBroadcast(intent)
    }

    fun openWebView(url: String, title : String){
        val intent = Intent(this, WebViewActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("URL", url)
        intent.putExtra("Title",title)
        startActivity(intent)
    }

    fun sendGA(category: String, action: String, label: String){
        getDefaultTracker()?.send(
            builder.setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build())
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }
}