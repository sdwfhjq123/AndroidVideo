package com.yinhao.wanandroid.ui.fragment.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.verticaltablayout.VerticalTabLayout
import com.yinhao.verticaltablayout.widget.TabView
import com.yinhao.wanandroid.databinding.FragmentNavBinding
import com.yinhao.wanandroid.model.bean.NavigationBean
import org.jetbrains.anko.support.v4.toast

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */
class NavFragment : BaseFragment<NavViewModel, FragmentNavBinding>() {
    private val mRightAdapter by lazy { NavInfoAdapter() }
    private val mLayoutManager by lazy { LinearLayoutManager(activity) }
    private var mCurrentIndex: Int = 0
    private var mBScroll: Boolean = false
    private var mBClickTab: Boolean = false

    companion object {
        fun newInstance(): NavFragment {
            val fragment =
                NavFragment()
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
        initRecyclerView()
        viewObserver()
    }

    override fun initData() {
        viewModel.getNavData()
    }

    private fun viewObserver() {
        viewModel.navData.observe(this) {
            it.isSuccess?.let { list ->
                initNavTabView(list)
            }
            it.isError?.let { err ->
                viewBinding?.multipleStatusView?.showError()
                toast(err)
            }
        }
    }

    private fun initNavTabView(list: List<NavigationBean>) {
        list.let {
            viewBinding?.navigationTabLayout?.setTabAdapter(
                NavigationTabAdapter(
                    activity,
                    list
                )
            )
            mRightAdapter.setNewData(it as MutableList<NavigationBean>)
        }
    }

    private fun initRecyclerView() {
        viewBinding?.recyclerViewRight?.run {
            layoutManager = mLayoutManager
            adapter = mRightAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (mBScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                        scrollRecyclerView()
                    }
                    rightLinkLeft(newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (mBScroll) {
                        scrollRecyclerView()
                    }
                }
            })
        }

        viewBinding?.navigationTabLayout?.addOnTabSelectedListener(object :
            VerticalTabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabView?, position: Int) {
            }

            override fun onTabSelected(tab: TabView?, position: Int) {
                mBClickTab = true
                selectTab(position)
            }
        })

    }

    private fun scrollRecyclerView() {
        mBScroll = false
        val indexDistance: Int = mCurrentIndex - mLayoutManager.findFirstVisibleItemPosition()
        if (indexDistance > 0 && indexDistance < viewBinding!!.recyclerViewRight.childCount) {
            val top: Int = viewBinding.recyclerViewRight.getChildAt(indexDistance).top
            viewBinding.recyclerViewRight.smoothScrollBy(0, top)
        }
    }

    /**
     * Right RecyclerView link Left TabLayout
     *
     * @param newState RecyclerView Scroll State
     */
    private fun rightLinkLeft(newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (mBClickTab) {
                mBClickTab = false
                return
            }
            val firstPosition: Int = mLayoutManager.findFirstVisibleItemPosition()
            if (firstPosition != mCurrentIndex) {
                mCurrentIndex = firstPosition
                setChecked(mCurrentIndex)
            }
        }
    }

    /**
     * Smooth Right RecyclerView to Select Left TabLayout
     *
     * @param position checked position
     */
    private fun setChecked(position: Int) {
        if (mBClickTab) {
            mBClickTab = false
        } else {
            viewBinding?.navigationTabLayout?.setTabSelected(mCurrentIndex)
        }
        mCurrentIndex = position
    }

    /**
     * Select Left TabLayout to Smooth Right RecyclerView
     */
    private fun selectTab(position: Int) {
        mCurrentIndex = position
        viewBinding?.recyclerViewRight?.stopScroll()
        mLayoutManager.scrollToPositionWithOffset(position, 0)
        mBScroll = true
    }

}