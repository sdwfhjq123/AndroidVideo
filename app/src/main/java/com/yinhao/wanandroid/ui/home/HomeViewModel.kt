package com.yinhao.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.logic.model.bean.BannerBean
import com.yinhao.wanandroid.logic.network.repository.HomeRepository
import com.yinhao.wanandroid.other.checkSuccess

/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description:
 */
class HomeViewModel : BaseViewModel() {
    private val _bannerData = MutableLiveData<List<BannerBean>>()
    val bannerData: LiveData<List<BannerBean>>
        get() = _bannerData

    fun getHomeBanner() {
        launchOnUI {
            val result = HomeRepository.getHomeBanner()
            result.checkSuccess {
                _bannerData.value = it
            }
        }
    }
}