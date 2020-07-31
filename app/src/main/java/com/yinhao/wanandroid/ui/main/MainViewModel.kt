package com.yinhao.wanandroid.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.commonmodule.base.utils.Preference
import com.yinhao.wanandroid.db.entity.User
import com.yinhao.wanandroid.network.repository.UserRepository
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description:
 */
class MainViewModel : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun logout() {
        viewModelScope.launch {
            Preference.clearPreference()
            UserRepository.deleteUser()
        }
    }

    fun getUser() {
        viewModelScope.launch {
            _user.value = UserRepository.getUser()[0]
        }
    }
}