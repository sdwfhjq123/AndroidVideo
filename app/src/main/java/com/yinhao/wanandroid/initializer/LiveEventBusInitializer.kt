package com.yinhao.wanandroid.initializer

import android.content.Context
import androidx.startup.Initializer
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * author:  yinhao
 * date:    2020/9/23
 * version: v1.0
 * ### description:
 */
class LiveEventBusInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        LiveEventBus.config()
            .autoClear(true)
            .supportBroadcast(context)
            .lifecycleObserverAlwaysActive(true)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return  mutableListOf()
    }
}