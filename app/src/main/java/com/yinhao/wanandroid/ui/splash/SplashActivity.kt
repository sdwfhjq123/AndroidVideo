package com.yinhao.wanandroid.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.yinhao.wanandroid.base.BaseVMActivity
import com.yinhao.wanandroid.databinding.ActivitySplashBinding

class SplashActivity : BaseVMActivity<SplashViewModel, ActivitySplashBinding>() {

    override fun initViewModel(): SplashViewModel =
        ViewModelProvider(this).get(SplashViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initData() {
    }
}