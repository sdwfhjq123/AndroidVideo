package com.yinhao.wanandroid.ui.fragment.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.model.bean.NavigationBean
import com.yinhao.wanandroid.network.repository.SystemRepository
import com.yinhao.wanandroid.other.checkResult
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */
class NavViewModel : BaseViewModel() {

    private val _navData = MutableLiveData<UiState<List<NavigationBean>>>()
    val navData: LiveData<UiState<List<NavigationBean>>>
        get() = _navData

    fun getNavData() {
        viewModelScope.launch {
            val result = SystemRepository.getNavData()
            result.checkResult({
                emitUiState(isSuccess = it)
            }, {
                emitUiState(isError = it)
            })
        }
    }

    private fun emitUiState(
        isLoading: Boolean = false,
        isRefresh: Boolean = false,
        isSuccess: List<NavigationBean>? = null,
        isError: String? = null
    ) {
        val uiState = UiState(
            isLoading, isRefresh, isSuccess, isError
        )
        _navData.value = uiState
    }
}