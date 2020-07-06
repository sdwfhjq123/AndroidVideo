package com.yinhao.wanandroid.ui.signon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.commonmodule.base.repository.RepositoryResult
import com.yinhao.wanandroid.logic.model.SignOnEntity
import com.yinhao.wanandroid.logic.network.repository.UserRepository
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description:
 */
class SignOnViewModel : BaseViewModel() {
    val result:LiveData<RepositoryResult<SignOnEntity>>
        get() {
            TODO()
        }

    //TODO 如何解决持有activity的问题 ，观察对象
    fun signOn() {
        viewModelScope.launch {
            val result = UserRepository.signOn("sdwfhjq123", "123456Aa", "123456Aa")
            if (result.errorCode == 0) {
                Log.e("请求成功", "请求成功")
            } else {
                Log.e("请求注册", result.errorMsg)
            }
        }
    }
}