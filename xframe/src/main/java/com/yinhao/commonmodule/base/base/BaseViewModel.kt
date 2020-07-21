package com.yinhao.commonmodule.base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.repository.livedata.holder.WaitingHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * author:  yinhao
 * date:    2020/4/2
 * version: v1.0
 * ### description:
 */

open class BaseViewModel : ViewModel() {

    val notSignLiveData by lazy { MutableLiveData<String>() }
    val noNetworkLiveData by lazy { MutableLiveData<String>() }
    val showMessageLiveData by lazy { MutableLiveData<String>() }
    val tokenOverTimeLiveData by lazy { MutableLiveData<String>() }

    //    val stateLayoutLiveData by lazy { MutableLiveData<StateHolder>() }
    val waitingViewLiveData by lazy { MutableLiveData<WaitingHolder>() }

    open class UiState<T>(
        val isLoading: Boolean = false,
        val isRefresh: Boolean = false,
        val isSuccess: T? = null,
        val isError: String? = null
    )

    open class BaseUiModel<T>(
        var showLoading: Boolean = false,
        var showError: String? = null,
        var showSuccess: T? = null,
        var showEnd: Boolean = false, // 加载更多
        var isRefresh: Boolean = false,// 刷新
        val needLogin: Boolean? = null
    )

    val mException: MutableLiveData<Throwable> = MutableLiveData()

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {

        viewModelScope.launch { block() }

    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }

}