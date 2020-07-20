package com.yinhao.wanandroid.ui.main.wechat.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.wanandroid.databinding.FragmentWxItemBinding

/**
 * author:  yinhao
 * date:    2020/7/20
 * version: v1.0
 * ### description:
 */
class WxItemFragment : BaseFragment<WxItemViewModel, FragmentWxItemBinding>() {

    companion object {
        fun newInstance(): Fragment{
            return WxItemFragment()
        }
    }

    override fun initViewModel(): WxItemViewModel =
        ViewModelProvider(this).get(WxItemViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWxItemBinding = FragmentWxItemBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initData() {
    }
}