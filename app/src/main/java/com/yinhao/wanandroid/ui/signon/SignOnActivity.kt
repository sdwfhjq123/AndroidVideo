package com.yinhao.wanandroid.ui.signon

import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ImmersionBar.setStatusBarView
import com.gyf.immersionbar.ktx.immersionBar
import com.yinhao.commonmodule.R
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.databinding.ActivitySignOnBinding
import com.yinhao.wanandroid.widget.ToolbarManager

class SignOnActivity : BaseActivity<SignOnViewModel, ActivitySignOnBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.toolbar }

    override fun initViewModel(): SignOnViewModel =
        ViewModelProvider(this).get(SignOnViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivitySignOnBinding =
        ActivitySignOnBinding.inflate(layoutInflater)

    override fun initWindowFlag() {
        immersionBar {
            fullScreen(true)
        }
        setStatusBarView(this, viewBinding?.viewImmersionbar)
    }

    override fun initEvents() {
    }

    override fun start() {
        enableHomeAsUp(1f) {
            finish()
        }
    }

}