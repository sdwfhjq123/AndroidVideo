package com.yinhao.wanandroid.ui.main.square

import android.view.*
import androidx.lifecycle.ViewModelProvider
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.wanandroid.databinding.FragmentSquareBinding

/**
 * author:  yinhao
 * date:    2020/7/8
 * version: v1.0
 * ### description:
 */
class SquareFragment : BaseFragment<SquareViewModel, FragmentSquareBinding>() {

    override fun initViewModel(): SquareViewModel =
        ViewModelProvider(this).get(SquareViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?)
            : FragmentSquareBinding = FragmentSquareBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initData() {
    }

}