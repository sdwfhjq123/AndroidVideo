package com.yinhao.wanandroid.ui.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.wanandroid.databinding.FragmentHomeBinding
import com.yinhao.wanandroid.ui.home.HomeViewModel
import com.youth.banner.indicator.CircleIndicator

/**
 * author:  yinhao
 * date:    2020/7/8
 * version: v1.0
 * ### description:
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initView() {
        initBanner()

        viewModelObserver()
    }

    private fun viewModelObserver() {
        viewModel.bannerData.observe(this) {
            viewBinding!!.banner.setDatas(it).start()
        }
    }

    private fun initBanner() {
        viewBinding!!.banner.addBannerLifecycleObserver(activity)//添加生命周期观察者
            .setAdapter(ImageAdapter(null)).indicator = CircleIndicator(activity)
    }

    override fun initData() {
        viewModel.getHomeBanner()
    }
}