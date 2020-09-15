package com.yinhao.wanandroid.ui.login

import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yinhao.wanandroid.base.BaseVMActivity
import com.yinhao.wanandroid.databinding.ActivityLoginBinding
import com.yinhao.wanandroid.event.LoginEvent
import com.yinhao.wanandroid.ui.register.SignOnActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import team.fcma.xframe.ex.click

class LoginActivity : BaseVMActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun initViewModel(): LoginViewModel =
        ViewModelProvider(this).get(LoginViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun initView() {
        viewBinding?.apply {
            etUser.addTextChangedListener {
                viewModel.setUserValue(it.toString())
                observeBtnIsEnable()
            }

            etPassword.addTextChangedListener {
                viewModel.setPasswordValue(it.toString())
                observeBtnIsEnable()
            }

            btnSignIn.click {
                viewModel.signIn()
            }
        }

        viewModel.apply {
            username.observe(this@LoginActivity) {
                viewBinding?.btnSignIn?.isEnabled = it.isNotEmpty() &&
                        viewModel.password.value?.isNotEmpty() ?: false
            }

            password.observe(this@LoginActivity) {
                viewBinding?.btnSignIn?.isEnabled = it.isNotEmpty() &&
                        viewModel.username.value?.isNotEmpty() ?: false
            }

            signData.observe(this@LoginActivity) {
                it.isLoading.let { getWaitingView().show() }

                it.isSuccess?.let { bean ->
                    setUser(bean)
                    prefIsLogin = true
                    hideWaitingView()
                    toast("登录成功")
                    LiveEventBus.get("login").post(LoginEvent(true))
                    finish()
                }
                it.isError?.let { err ->
                    prefIsLogin = false
                    hideWaitingView()
                    toast(err)
                }
                viewBinding?.btnSignIn?.isEnabled = it.enableSignInButton
            }
        }
    }

    override fun initData() {
        tv_sign_on.click {
            startActivity<SignOnActivity>()
        }

        viewBinding?.etPassword?.setText("123456Aa")
        viewBinding?.etUser?.setText("sdwfhjq123")

    }

    private fun observeBtnIsEnable() {
        viewBinding?.btnSignIn?.isEnabled = viewModel.username.value?.isNotEmpty() ?: false
                && viewModel.password.value?.isNotEmpty() ?: false
    }


}