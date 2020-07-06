package com.yinhao.wanandroid.ui.home

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {
    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this).get(HomeViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityHomeBinding =
        ActivityHomeBinding.inflate(inflater)

    override fun initWindowFlag() {
    }

    override fun initEvents() {
    }

    override fun start() {
    }

}