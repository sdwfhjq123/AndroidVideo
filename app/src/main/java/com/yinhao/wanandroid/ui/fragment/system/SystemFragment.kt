package com.yinhao.wanandroid.ui.fragment.system

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.yinhao.wanandroid.base.BaseFragment
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.FragmentSystemBinding
import com.yinhao.wanandroid.ui.fragment.navigation.NavFragment
import com.yinhao.wanandroid.ui.fragment.knowledgeTree.KnowTreeFragment

/**
 * author:  yinhao
 * date:    2020/7/8
 * version: v1.0
 * ### description:
 */
class SystemFragment : BaseFragment<SystemViewModel, FragmentSystemBinding>() {
    private val mTabList = mutableListOf<Fragment>()

    init {
        mTabList.add(KnowTreeFragment.newInstance())
        mTabList.add(NavFragment.newInstance())
    }

    override fun initViewModel(): SystemViewModel =
        ViewModelProvider(this).get(SystemViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSystemBinding = FragmentSystemBinding.inflate(layoutInflater)

    override fun initView() {
        initTab()
    }

    override fun initData() {
    }

    private fun initTab() {
        viewBinding?.viewPager?.apply {
            offscreenPageLimit = 2
            adapter = object : FragmentStateAdapter(this@SystemFragment) {
                override fun getItemCount(): Int {
                    return mTabList.size
                }

                override fun createFragment(position: Int): Fragment {
                    // 实例化Fragment
                    return mTabList[position]
                }
            }
        }

        TabLayoutMediator(viewBinding!!.tabLayout, viewBinding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                if (position == 0) {
                    tab.text = resources.getString(R.string.item_tab_system)
                } else {
                    tab.text = resources.getString(R.string.item_tab_nav)
                }
            }
        ).attach()
    }

}