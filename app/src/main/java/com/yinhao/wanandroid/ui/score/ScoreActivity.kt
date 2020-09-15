package com.yinhao.wanandroid.ui.score

import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.yinhao.wanandroid.base.BaseVMActivity
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivityScoreBinding
import com.yinhao.wanandroid.widget.ToolbarManager
import org.jetbrains.anko.toast

class ScoreActivity : BaseVMActivity<ScoreViewModel, ActivityScoreBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.toolbar }
    private val mAdapter by lazy { ScoreAdapter() }

    override fun initViewModel(): ScoreViewModel =
        ViewModelProvider(this).get(ScoreViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityScoreBinding =
        ActivityScoreBinding.inflate(layoutInflater)

    override fun initView() {
        initToolbar()
        initRecyclerView()
        initRefresh()
        viewObserver()
    }

    override fun initData() {
        viewModel.getScoreViewList(true)
    }

    private fun viewObserver() {
        viewModel.uiState.observe(this) {
            it.showSuccess?.let { list ->
                mAdapter.run {
                    if (it.isRefresh) {
                        replaceData(list)
                        viewBinding?.refreshLayout?.finishRefresh()
                    } else {
                        addData(list)
                        viewBinding?.refreshLayout?.finishLoadMore(true)
                    }
                }
                viewBinding?.multipleStatusView?.showContent()
            }

            if (it.showEnd) viewBinding?.refreshLayout?.finishLoadMoreWithNoMoreData()

            it.showError?.let { message ->
                toast(if (message.isBlank()) "网络异常" else message)
                viewBinding?.multipleStatusView?.showError()
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbarTitle = resources.getString(R.string.nav_my_collect)
        enableHomeAsUp(1f) { finish() }
        enableMenu()
    }

    private fun initRecyclerView() {
        viewBinding?.recyclerView?.run {
            layoutManager = LinearLayoutManager(this@ScoreActivity)
            adapter = mAdapter
        }
    }

    private fun initRefresh() {
        viewBinding?.refreshLayout?.run {
            setRefreshHeader(ClassicsHeader(this@ScoreActivity))
            setRefreshFooter(ClassicsFooter(this@ScoreActivity))
            setOnRefreshLoadMoreListener(object :
                OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    viewModel.getScoreViewList(false)
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    viewModel.getScoreViewList(true)
                }
            })
        }
    }
}