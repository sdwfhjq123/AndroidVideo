package com.yinhao.wanandroid.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.logic.model.bean.SignOnEntity
import com.yinhao.wanandroid.logic.network.repository.UserRepository
import com.yinhao.wanandroid.other.checkResult
import com.yinhao.wanandroid.ui.signon.SignOnUiModel

/**
 * author:  yinhao
 * date:    2020/7/2
 * version: v1.0
 * ### description:
 */

class SignInViewModel : BaseViewModel() {

    private val _signData = MutableLiveData<SignInUiModel<SignOnEntity>>()
    val signData: LiveData<SignInUiModel<SignOnEntity>>
        get() = _signData

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

    fun signIn() {
        launchOnUI {
            val result = UserRepository.signIn(_username.value!!, _password.value!!)
            result.checkResult(
                onSuccess = {
                    _signData.value = SignInUiModel(isSuccess = it, enableSignInButton = true)
                },
                onError = {
                    _signData.value = SignInUiModel(isError = it, enableSignInButton = true)
                }
            )
        }
    }
}

data class SignInUiModel<T>(
    val isLoading: Boolean = false,
    val isSuccess: T? = null,
    val isError: String? = null,
    val enableSignInButton: Boolean = false
)