package com.yinhao.commonmodule.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ActivityUtils
import com.gyf.immersionbar.ktx.immersionBar
import com.kaopiz.kprogresshud.KProgressHUD
import com.yinhao.commonmodule.R
import com.yinhao.commonmodule.base.utils.Preference
import org.jetbrains.anko.find

/**
 * author:  yinhao
 * date:    2020/4/2
 * version: v1.0
 * ### description:
 */

abstract class BaseVMActivity<M : BaseViewModel, B : ViewBinding>
    : AppCompatActivity(), ViewBindingProxy<B> by ViewBindingDelegate<B>() {

    open var barDarkMode = false
    private var waitingView: KProgressHUD? = null
    private var noNetworkAlert: MaterialDialog? = null
    private var notSignedAlert: MaterialDialog? = null
    private var tokenOvertimeAlert: MaterialDialog? = null
    protected val viewModel by lazy { initViewModel() }

    protected var prefIsLogin by Preference("LOGIN_KEY", false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtils.getActivityList().add(this)
        setContentView(bindViewBinding(initViewBinging(layoutInflater), this))
        setupStatusBar()
        initView()
        initData()
    }

    override fun onDestroy() {
        waitingView?.dismiss()
        noNetworkAlert?.dismiss()
        notSignedAlert?.dismiss()
        tokenOvertimeAlert?.dismiss()
        waitingView = null
        noNetworkAlert = null
        notSignedAlert = null
        tokenOvertimeAlert = null
        super.onDestroy()
        ActivityUtils.getActivityList().remove(this)
    }

    /**
     * ### 设置statusBar
     */
    private fun setupStatusBar() {
        find<View>(R.id.view_immersionbar)?.let {
            immersionBar {
                fullScreen(true)
                keyboardEnable(true)
                statusBarView(it)
            }
        }
    }

    /**
     * ### 获取等待View
     */
    protected fun getWaitingView(): KProgressHUD {
        waitingView?.dismiss()
        waitingView = waitingView ?: (KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setLabel(getString(R.string.pleaseWaiting))
            .setAnimationSpeed(2)
            .setDimAmount(0.5f))
        return waitingView!!
    }

    /**
     * ### 隐藏等待View
     */
    protected fun hideWaitingView() {
        waitingView?.dismiss()
    }

    /**
     * ### 获取本页面对应的ViewModel
     */
    abstract fun initViewModel(): M

    /**
     * ### 获取本页面对应的ViewBinding
     */
    abstract fun initViewBinging(inflater: LayoutInflater): B

    abstract fun initView()
    abstract fun initData()

}