package com.yinhao.wanandroid.ui.fragment.square

import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.yinhao.wanandroid.base.BaseFragment
import com.yinhao.wanandroid.databinding.FragmentSquareBinding
import com.yinhao.wanandroid.model.bean.ArticleBean
import com.yinhao.wanandroid.ui.content.ContentActivity
import com.yinhao.wanandroid.ui.fragment.home.ArticleAdapter
import org.jetbrains.anko.toast

/**
 * author:  yinhao
 * date:    2020/7/8
 * version: v1.0
 * ### description:
 */
class SquareFragment : BaseFragment<SquareViewModel, FragmentSquareBinding>() {
    private val mAdapter by lazy { ArticleAdapter() }

    override fun initViewModel(): SquareViewModel =
        ViewModelProvider(this).get(SquareViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?)
            : FragmentSquareBinding = FragmentSquareBinding.inflate(layoutInflater)

    override fun initView() {
        viewBinding?.multipleStatusView?.showLoading()
        initRecyclerView()
        initRefresh()
        viewModelObserver()

        mAdapter.setOnItemClickListener { adapter, view, position ->
            if (adapter.data.size != 0) {
                val data = adapter.data[position] as ArticleBean
                ContentActivity.actionStart(activity, data.id, data.title, data.link)
            }
        }
    }

    private fun viewModelObserver() {
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
                activity?.toast(if (message.isBlank()) "网络异常" else message)
                viewBinding?.multipleStatusView?.showError()
            }
        }
    }

    override fun initData() {
        viewModel.getSquareList(true)
    }

    private fun initRecyclerView() {
        viewBinding?.recyclerView?.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
    }

    private fun initRefresh() {
        viewBinding?.refreshLayout?.run {
            setRefreshHeader(ClassicsHeader(activity))
            setRefreshFooter(ClassicsFooter(activity))
            setOnRefreshLoadMoreListener(object :
                OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    viewModel.getSquareList(false)
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    viewModel.getSquareList(true)
                }
            })
        }
    }

}