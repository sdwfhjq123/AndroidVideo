package com.yinhao.wanandroid

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.Glide
import com.jeremyliao.liveeventbus.LiveEventBus
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.yinhao.commonmodule.base.utils.Preference


/**
 * author:  yinhao
 * date:    2020/4/2
 * version: v1.0
 * ### description:
 */

class App : Application() {
    private val nightModePref by Preference<Boolean>("getIsNightMode", false)

    companion object {
        @JvmStatic
        lateinit var instance: App
            private set

        init {
            //设置全局的Header构建器

            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                //全局设置主题颜色
                layout.setPrimaryColorsId(
                    R.color.colorPrimary,
                    R.color.White
                )
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                ClassicsHeader(context)
            }
            //设置全局的Footer构建器
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)
        initLiveEventBus()

        MultiDex.install(this)

        if (nightModePref) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
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