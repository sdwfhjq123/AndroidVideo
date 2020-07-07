package com.yinhao.wanandroid.ui.signin

import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.databinding.ActivitySignInBinding
import com.yinhao.wanandroid.ui.signon.SignOnActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity
import team.fcma.xframe.ex.click

class SignInActivity : BaseActivity<SignInViewModel, ActivitySignInBinding>() {

    override fun initViewModel(): SignInViewModel =
        ViewModelProvider(this).get(SignInViewModel::class.java)

    override fun initWindowFlag() {
        immersionBar {
            fullScreen(true)
        }
        ImmersionBar.setStatusBarView(this, viewBinding?.viewImmersionbar)
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

    }

    override fun start() {
        tv_sign_on.click {
            startActivity<SignOnActivity>()
        }
    }

    override fun initViewBinging(inflater: LayoutInflater): ActivitySignInBinding =
        ActivitySignInBinding.inflate(layoutInflater)

}