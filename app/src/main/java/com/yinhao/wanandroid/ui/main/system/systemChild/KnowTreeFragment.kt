package com.yinhao.wanandroid.ui.main.system.systemChild

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
import com.yinhao.wanandroid.databinding.FragmentKnowTreeBinding
import com.yinhao.wanandroid.logic.model.bean.KnowledgeTreeBean
import org.jetbrains.anko.toast

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */
class KnowTreeFragment : BaseFragment<KnowTreeViewModel, FragmentKnowTreeBinding>() {
    private val mAdapter by lazy { KnowledgeTreeAdapter() }

    companion object {
        fun newInstance(): KnowTreeFragment {
            val fragment = KnowTreeFragment()
            return fragment
        }
    }

    override fun initViewModel(): KnowTreeViewModel =
        ViewModelProvider(this).get(KnowTreeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentKnowTreeBinding = FragmentKnowTreeBinding.inflate(layoutInflater)

    override fun initView() {
        viewBinding?.multipleStatusView?.showLoading()
        initRecyclerView()
        initRefresh()
        viewObserver()
    }

    override fun initData() {
        viewModel.getSystemData()
    }

    private fun viewObserver() {
        viewModel.systemData.observe(this) {
            it.isSuccess?.let { list ->
                mAdapter.replaceData(list)
                viewBinding?.multipleStatusView?.showContent()
                viewBinding?.refreshLayout?.finishRefresh()
            }

            it.isError?.let { err ->
                activity?.toast(err)
//                viewBinding?.multipleStatusView?.showError()
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
            setOnRefreshLoadMoreListener(object :
                OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    viewModel.getSystemData()
                }
            })
        }
    }
}