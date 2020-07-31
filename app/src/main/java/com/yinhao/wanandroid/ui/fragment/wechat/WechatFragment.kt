package com.yinhao.wanandroid.ui.fragment.wechat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.wanandroid.databinding.FragmentWechatBinding
import com.yinhao.wanandroid.model.bean.WXChapterBean
import com.yinhao.wanandroid.ui.fragment.knowledge.KnowledgeFragment

/**
 * author:  yinhao
 * date:    2020/7/8
 * version: v1.0
 * ### description:
 */
class WechatFragment : BaseFragment<WechatViewModel, FragmentWechatBinding>() {
    override fun initViewModel(): WechatViewModel =
        ViewModelProvider(this).get(WechatViewModel::class.java)

    private var mTabList = listOf<WXChapterBean>()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWechatBinding = FragmentWechatBinding.inflate(layoutInflater)

    override fun initView() {
        viewModelObserver()
    }

    override fun initData() {
        viewModel.getWxTabList()
    }

    private fun initTab() {
        viewBinding?.viewPager?.apply {
            offscreenPageLimit = 2
            adapter = object : FragmentStateAdapter(this@WechatFragment) {
                override fun getItemCount(): Int {
                    return mTabList.size
                }

                override fun createFragment(position: Int): Fragment {
                    // 实例化Fragment
                    return KnowledgeFragment.newInstance(mTabList[position].id)
                }
            }
        }

        TabLayoutMediator(viewBinding!!.tabLayout, viewBinding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = mTabList[position].name
            }
        ).attach()
    }

    private fun viewModelObserver() {
        viewModel.tabUiState.observe(this) {
            it.isSuccess.let { list ->
                mTabList = list!!
                initTab()
            }
        }
    }

}