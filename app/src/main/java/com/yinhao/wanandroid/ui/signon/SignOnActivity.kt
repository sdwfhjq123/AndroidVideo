package com.yinhao.wanandroid.ui.signon

import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.gyf.immersionbar.ImmersionBar.setStatusBarView
import com.gyf.immersionbar.ktx.immersionBar
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.databinding.ActivitySignOnBinding
import com.yinhao.wanandroid.widget.ToolbarManager
import org.jetbrains.anko.toast
import team.fcma.xframe.ex.click

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

        viewModel.apply {
            signData.observe(this@SignOnActivity) {
                it.isLoading.let { getWaitingView().show() }

                it.isSuccess?.let {
                    hideWaitingView()
                    toast("注册成功")
                    finish()
                }

                it.isError?.let { err ->
                    hideWaitingView()
                    toast(err)
                }

                viewBinding?.btnSignOn?.isEnabled = it.enableSignOnButton
            }

        }

        viewBinding?.apply {
            etUser.addTextChangedListener {
                viewModel.setUserValue(it.toString())
                observeBtnIsEnable()
            }
            etPassword.addTextChangedListener {
                viewModel.setPasswordValue(it.toString())
                observeBtnIsEnable()
            }
            etRepeatPassword.addTextChangedListener {
                viewModel.setRepeatPwdValue(it.toString())
                observeBtnIsEnable()
            }
            btnSignOn.click {
                if (viewModel.password.value.equals(viewModel.password.value)) {
                    viewModel.signOn()
                } else {
                    viewBinding.etRepeatPassword.error = "两次密码验证错误"
                }
            }
        }

    }

    private fun observeBtnIsEnable() {
        viewBinding?.btnSignOn?.isEnabled = viewModel.username.value?.isNotEmpty() ?: false
                && viewModel.password.value?.isNotEmpty() ?: false
                && viewModel.repeatPwd.value?.isNotEmpty() ?: false
    }

    override fun start() {
        enableHomeAsUp(1f) {
            finish()
        }
    }

}