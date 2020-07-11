package com.yinhao.commonmodule.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ktx.immersionBar

/**
 * author:      SHIGUANG
 * date:        2018/10/17
 * version:     v1.0
 * ### description: 基础Fragment
 */
abstract class BaseFragment<M : BaseViewModel, B : ViewBinding>
    : Fragment(), ViewBindingProxy<B> by ViewBindingDelegate<B>() {

    open var barDarkMode = false
    protected val viewModel: M by lazy { initViewModel() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        viewBinding?.root ?: bindViewBinding(initViewBinding(inflater, container), this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupStatusBar()
    }

    /**
     * ### 设置statusBar
     */
    private fun setupStatusBar() {
        immersionBar {
            keyboardEnable(true)
            statusBarDarkFont(barDarkMode)
        }
    }

    /**
     * ### 建立共享ViewModel
     */
//    protected fun setupSharedViewModel(block: (hostActivity: FragmentActivity) -> SM): SM {
//        return activity?.run { block.invoke(this) }
//            ?: throw IllegalStateException("未能找到对应的Activity：" + this@BaseFragment::class.simpleName)
//    }

    /**
     * ### 获取本页面对应的ViewModel
     */
    abstract fun initViewModel(): M

    /**
     * ### 获取本页面对应的共享ViewModel
     */
//    abstract fun initSharedViewModel(): SM

    /**
     * ### 获取本页面对应的ViewBinding
     */
    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun initView()

    abstract fun initData()

}
