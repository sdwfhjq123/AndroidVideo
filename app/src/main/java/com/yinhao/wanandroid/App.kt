package com.yinhao.wanandroid

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.Glide
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * author:  yinhao
 * date:    2020/4/2
 * version: v1.0
 * ### description:
 */

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)
        initLiveEventBus()
    }

    private fun initLiveEventBus() {
        LiveEventBus.config()
            .autoClear(true)
            .supportBroadcast(this)
            .lifecycleObserverAlwaysActive(true)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).onTrimMemory(level)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}