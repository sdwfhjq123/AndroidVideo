package com.yinhao.wanandroid.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yinhao.commonmodule.base.base.BaseViewModel

/**
 * author:  yinhao
 * date:    2020/7/2
 * version: v1.0
 * ### description:
 */

class SignInViewModel : BaseViewModel() {

    private val _username = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val username: LiveData<String>
        get() = _username
    val password: LiveData<String>
        get() = _password

    fun setUserValue(username: String) {
        _username.value = username
    }

    fun setPasswordValue(password: String) {
        _password.value = password
    }
}