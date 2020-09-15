package com.yinhao.wanandroid.ui.fragment.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yinhao.wanandroid.base.BaseFragment
import com.yinhao.wanandroid.databinding.FragmentCollectionBinding

/**
 * author:  yinhao
 * date:    2020/8/5
 * version: v1.0
 * ### description:
 */
class CollectionFragment : BaseFragment<CollectionViewModel, FragmentCollectionBinding>() {

    companion object {
        fun newInstance(): CollectionFragment {
            val fragment = CollectionFragment()
            return fragment
        }
    }

    override fun initViewModel(): CollectionViewModel =
        ViewModelProvider(this).get(CollectionViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCollectionBinding = FragmentCollectionBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initData() {
    }
}