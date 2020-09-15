package com.yinhao.wanandroid.event

import com.yinhao.wanandroid.utils.SettingUtil


/**
 * Created by chenxz on 2018/6/18.
 */
class ColorEvent(var isRefresh: Boolean, var color: Int = SettingUtil.getColor())