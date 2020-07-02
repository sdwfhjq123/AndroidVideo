package com.yinhao.wanandroid

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.yinhao.wanandroid.databinding.ActivityLoginBinding
import com.yinhao.commonmodule.base.base.BaseActivity

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {
    override fun initViewModel(): LoginViewModel =
        ViewModelProvider(this).get(LoginViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityLoginBinding =
        ActivityLoginBinding.inflate(inflater)

    override fun initEvents() {
    }

    override fun start() {
    }

    override fun initWindowFlag() {
    }

}