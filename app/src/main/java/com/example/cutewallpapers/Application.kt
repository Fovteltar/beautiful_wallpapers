package com.example.cutewallpapers

import android.app.Application
import android.content.Context

class MainApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

//        fun isInternetAvailable(): Boolean {
//            val connectivityManager = getSystemService(applicationContext(), ConnectivityManager::class.java)
//            val activeNetworkInfo = if (connectivityManager != null) connectivityManager.activeNetworkInfo else null
//            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//        }

        fun getCacheDirectory() = applicationContext().cacheDir!!
    }
}



//