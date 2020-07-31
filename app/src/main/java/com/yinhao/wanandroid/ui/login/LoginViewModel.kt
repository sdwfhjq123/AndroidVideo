package com.yinhao.wanandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.db.entity.User
import com.yinhao.wanandroid.model.bean.UserBean
import com.yinhao.wanandroid.network.repository.UserRepository
import com.yinhao.wanandroid.other.checkResult
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/2
 * version: v1.0
 * ### description:
 */

class LoginViewModel : BaseViewModel() {

    private val _signData = MutableLiveData<SignInUiModel<UserBean>>()
    val signData: LiveData<SignInUiModel<UserBean>>
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
        _signData.value = SignInUiModel(isLoading = true)
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

    fun setUser(bean: UserBean) {
        viewModelScope.launch {
            UserRepository.setUser(bean)
        }
    }

}

data class SignInUiModel<T>(
    val isLoading: Boolean = false,
    val isSuccess: T? = null,
    val isError: String? = null,
    val enableSignInButton: Boolean = false
)