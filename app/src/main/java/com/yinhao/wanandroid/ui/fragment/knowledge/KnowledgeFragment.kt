package com.yinhao.wanandroid.ui.fragment.knowledge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.wanandroid.databinding.FragmentKnowledgeBinding
import com.yinhao.wanandroid.ui.fragment.home.ArticleAdapter
import org.jetbrains.anko.toast

/**
 * author:  yinhao
 * date:    2020/7/20
 * version: v1.0
 * ### description:
 */
class KnowledgeFragment : BaseFragment<KnowledgeViewModel, FragmentKnowledgeBinding>() {
    private val mAdapter by lazy { ArticleAdapter() }
    private var mId: Int = 0

    companion object {

        fun newInstance(id: Int): KnowledgeFragment {
            val args = Bundle()
            args.putInt("id", id)
            val fragment =
                KnowledgeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initViewModel(): KnowledgeViewModel =
        ViewModelProvider(this).get(KnowledgeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentKnowledgeBinding = FragmentKnowledgeBinding.inflate(layoutInflater)

    override fun initView() {
        viewBinding?.multipleStatusView?.showLoading()
        initRecyclerView()
        initRefresh()
        viewObserver()
    }

    override fun initData() {
        mId = arguments?.getInt("id") ?: 0
        viewModel.getArticleHistoryByWx(mId, true)
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
                activity?.toast(if (message.isBlank()) "网络异常" else message)
                viewBinding?.multipleStatusView?.showError()
            }
        }
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
                    viewModel.getArticleHistoryByWx(mId, false)
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    viewModel.getArticleHistoryByWx(mId, true)
                }
            })
        }
    }
}