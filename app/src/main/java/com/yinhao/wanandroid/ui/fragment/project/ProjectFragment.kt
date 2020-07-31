package com.yinhao.wanandroid.ui.fragment.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.wanandroid.databinding.FragmentProjectBinding
import com.yinhao.wanandroid.model.bean.ProjectTreeBean
import com.yinhao.wanandroid.ui.fragment.projectList.ProjectListFragment
import org.jetbrains.anko.support.v4.toast

/**
 * author:  yinhao
 * date:    2020/7/8
 * version: v1.0
 * ### description:
 */
class ProjectFragment : BaseFragment<ProjectViewModel, FragmentProjectBinding>() {
    private var mTabList = listOf<ProjectTreeBean>()

    override fun initViewModel(): ProjectViewModel =
        ViewModelProvider(this).get(ProjectViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProjectBinding = FragmentProjectBinding.inflate(layoutInflater)

    override fun initView() {
        viewModelObserver()
    }

    override fun initData() {
        viewModel.getWxTabList()
    }

    private fun viewModelObserver() {
        viewModel.tabUiState.observe(this) {
            it.isSuccess.let { list ->
                mTabList = list!!
                initTab()
            }

            it.isError?.let { toast(it) }
        }
    }

    private fun initTab() {
        viewBinding?.viewPager?.apply {
            offscreenPageLimit = 2
            adapter = object : FragmentStateAdapter(this@ProjectFragment) {
                override fun getItemCount(): Int {
                    return mTabList.size
                }

                override fun createFragment(position: Int): Fragment {
                    return ProjectListFragment.newInstance(mTabList[position].id)
                }
            }
        }

        TabLayoutMediator(viewBinding!!.tabLayout, viewBinding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = mTabList[position].name
            }
        ).attach()
    }

}