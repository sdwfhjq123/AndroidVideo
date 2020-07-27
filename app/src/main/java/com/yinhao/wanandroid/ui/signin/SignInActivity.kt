package com.yinhao.wanandroid.ui.signin

import android.content.Context
import android.view.LayoutInflater
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.databinding.ActivitySignInBinding
import com.yinhao.wanandroid.other.ConstantValues
import com.yinhao.wanandroid.ui.main.MainActivity
import com.yinhao.wanandroid.ui.signon.SignOnActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import team.fcma.xframe.ex.click

class SignInActivity : BaseActivity<SignInViewModel, ActivitySignInBinding>() {

    override fun initViewModel(): SignInViewModel =
        ViewModelProvider(this).get(SignInViewModel::class.java)

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
            username.observe(this@SignInActivity) {
                viewBinding?.btnSignIn?.isEnabled = it.isNotEmpty() &&
                        viewModel.password.value?.isNotEmpty() ?: false
            }

            password.observe(this@SignInActivity) {
                viewBinding?.btnSignIn?.isEnabled = it.isNotEmpty() &&
                        viewModel.username.value?.isNotEmpty() ?: false
            }

            signData.observe(this@SignInActivity) {
                it.isLoading.let { getWaitingView().show() }

                it.isSuccess?.let {
                    //TODO 用sp存储登录信息，然后点击判断sp存储。实现跳转loginActivity,然后MainActivity用livedataevents判断，其他的用sp
                    //TODO 用room实现用户信息表
                    getSharedPreferences(ConstantValues.PREF_NAME, Context.MODE_PRIVATE).edit {
                        putString(ConstantValues.USERNAME_KEY, it.username)
                    }
                    hideWaitingView()
                    toast("登录成功")
                    LiveEventBus.get("login").post(true)
                    finish()
                }
                it.isError?.let { err ->
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

    override fun initViewBinging(inflater: LayoutInflater): ActivitySignInBinding =
        ActivitySignInBinding.inflate(layoutInflater)

}