package com.yinhao.wanandroid.ui.main.system.nav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.wanandroid.databinding.FragmentNavBinding

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */
class NavFragment : BaseFragment<NavViewModel, FragmentNavBinding>() {
    companion object {
        fun newInstance(): NavFragment {
            val fragment = NavFragment()
            return fragment
        }
    }

    override fun initViewModel(): NavViewModel =
        ViewModelProvider(this).get(NavViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavBinding = FragmentNavBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initData() {
    }
}