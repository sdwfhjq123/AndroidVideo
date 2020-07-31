package com.yinhao.wanandroid.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.model.bean.UserBean
import com.yinhao.wanandroid.network.repository.UserRepository
import com.yinhao.wanandroid.other.checkResult
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description:
 */
class SignOnViewModel : BaseViewModel() {
    private val _signData = MutableLiveData<SignOnUiModel<UserBean>>()
    val signData: LiveData<SignOnUiModel<UserBean>>
        get() = _signData

    private val _username = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _repeatPwd = MutableLiveData<String>()

    val username: LiveData<String>
        get() = _username
    val password: LiveData<String>
        get() = _password
    val repeatPwd: LiveData<String>
        get() = _repeatPwd

    fun setUserValue(username: String) {
        _username.value = username
    }

    fun setPasswordValue(password: String) {
        _password.value = password
    }

    fun setRepeatPwdValue(repeatPwd: String) {
        _repeatPwd.value = repeatPwd
    }

    fun signOn() {
        //需要转换一下转换成success与failed
        _signData.value = SignOnUiModel(isLoading = true)
        viewModelScope.launch {
            val result = UserRepository.signOn("sdwfhjq123", "123456Aa", "123456Aa")
            result.checkResult(
                onSuccess = {
                    _signData.value = SignOnUiModel(isSuccess = it, enableSignOnButton = true)
                },
                onError = {
                    _signData.value = SignOnUiModel(isError = it, enableSignOnButton = true)
                }
            )
        }
    }
}

data class SignOnUiModel<T>(
    val isLoading: Boolean = false,
    val isSuccess: T? = null,
    val isError: String? = null,
    val enableSignOnButton: Boolean = false
)