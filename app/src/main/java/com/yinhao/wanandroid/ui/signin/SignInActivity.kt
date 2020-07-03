package com.yinhao.wanandroid.ui.signin

import android.util.Log
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.databinding.ActivitySignInBinding
import team.fcma.xframe.ex.click

class SignInActivity : BaseActivity<SignInViewModel, ActivitySignInBinding>() {

    override fun initViewModel(): SignInViewModel =
        ViewModelProvider(this).get(SignInViewModel::class.java)

    override fun initWindowFlag() {
    }

    override fun initEvents() {
        viewBinding?.etUser?.addTextChangedListener {
            viewModel.setUserValue(it.toString())
        }

        viewBinding?.etPassword?.addTextChangedListener {
            viewModel.setPasswordValue(it.toString())
        }

        viewModel.username.observe(this) {
            viewBinding?.btnSignIn?.isEnabled = it.isNotEmpty() &&
                    viewModel.password.value?.isNotEmpty() ?: false
        }

        viewModel.password.observe(this) {
            viewBinding?.btnSignIn?.isEnabled = it.isNotEmpty() &&
                    viewModel.username.value?.isNotEmpty() ?: false
        }
        viewBinding?.btnSignIn?.click {

        }

        viewBinding?.etUser?.setText("1111111111")
    }

    override fun start() {
    }

    override fun initViewBinging(inflater: LayoutInflater): ActivitySignInBinding =
        ActivitySignInBinding.inflate(layoutInflater)

}