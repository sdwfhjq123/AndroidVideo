package com.yinhao.wanandroid.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.ActivityUtils
import com.gyf.immersionbar.ktx.immersionBar
import com.kaopiz.kprogresshud.KProgressHUD
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.commonmodule.base.base.ViewBindingDelegate
import com.yinhao.commonmodule.base.base.ViewBindingProxy
import com.yinhao.commonmodule.base.utils.Preference
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.utils.SettingUtil
import com.yinhao.wanandroid.utils.StatusBarUtil
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
    private var isLogoutAlert: MaterialDialog? = null
    private var tokenOvertimeAlert: MaterialDialog? = null
    protected val viewModel by lazy { initViewModel() }

    protected var prefIsLogin by Preference("LOGIN_KEY", false)

    /**
     * theme color
     */
    protected var mThemeColor = SettingUtil.getColor()

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
        isLogoutAlert = null
        super.onDestroy()
        ActivityUtils.getActivityList().remove(this)
    }

    /**
     * ### 设置statusBar
     */
    private fun setupStatusBar() {
        find<View>(R.id.view_immersionbar).let {
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
     * ### 获取没有登录的提示框
     */
    protected fun getNotSignedAlert(block: () -> Unit): MaterialDialog {
        notSignedAlert?.dismiss()
        notSignedAlert = notSignedAlert ?: (MaterialDialog(this)
            .lifecycleOwner(this)
            .title(res = R.string.notSigned)
            .message(res = R.string.notSigned_content)
            .negativeButton(R.string.cancel) { it.dismiss() }
            .positiveButton(R.string.goSignIn) {
                it.dismiss()
                block()
            })
        return notSignedAlert!!
    }

    /**
     * ### 获取没有登录的提示框
     */
    protected fun getIsLogoutAlert(block: () -> Unit): MaterialDialog {
        isLogoutAlert?.dismiss()
        isLogoutAlert = isLogoutAlert ?: (MaterialDialog(this)
            .lifecycleOwner(this)
            .title(res = R.string.notSigned)
            .icon(R.drawable.xf_ic_empty)
            .message(text = "是否选择退出，退出后部分功能将无法使用")
            .negativeButton(R.string.cancel) { it.dismiss() }
            .positiveButton(R.string.exit) {
                it.dismiss()
                block()
            })
        return isLogoutAlert!!
    }

    /**
     * ### 隐藏等待View
     */
    protected fun hideWaitingView() {
        waitingView?.dismiss()
    }

    open fun initColor() {
        mThemeColor = if (!SettingUtil.getIsNightMode()) {
            SettingUtil.getColor()
        } else {
            getColor(R.color.colorPrimary)
        }
        StatusBarUtil.setColor(this, mThemeColor, 0)
        if (this.supportActionBar != null) {
            this.supportActionBar?.setBackgroundDrawable(ColorDrawable(mThemeColor))
        }

        if (SettingUtil.getNavBar()) {
            window.navigationBarColor = mThemeColor
        } else {
            window.navigationBarColor = Color.BLACK
        }
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