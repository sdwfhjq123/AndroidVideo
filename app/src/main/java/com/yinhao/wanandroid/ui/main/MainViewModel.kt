package com.yinhao.wanandroid.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.commonmodule.base.utils.Preference
import com.yinhao.wanandroid.db.entity.User
import com.yinhao.wanandroid.model.bean.UserInfoBody
import com.yinhao.wanandroid.network.repository.UserRepository
import com.yinhao.wanandroid.other.checkSuccess
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description:
 */
class MainViewModel : BaseViewModel() {

    private val _userScore = MutableLiveData<UserInfoBody>()
    val userScore: LiveData<UserInfoBody>
        get() = _userScore

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun logout() {
        viewModelScope.launch {
            Preference.clearPreference()
            UserRepository.deleteUser()
            _user.value = null
            _userScore.value = null
        }
    }

    fun getUser() {
        viewModelScope.launch {
            val userList = UserRepository.getUser()
            if (userList.isNullOrEmpty()) {
                _user.value = null
            } else {
                _user.value = userList[0]
            }
        }
    }

    fun getUserScore() {
        viewModelScope.launch {
            val result = UserRepository.getUserScore()
            result.checkSuccess {
                _userScore.value = it
            }
        }
    }
}