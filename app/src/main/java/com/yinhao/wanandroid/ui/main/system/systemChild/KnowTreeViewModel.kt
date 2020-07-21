package com.yinhao.wanandroid.ui.main.system.systemChild

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.logic.model.bean.KnowledgeTreeBean
import com.yinhao.wanandroid.logic.model.bean.WXChapterBean
import com.yinhao.wanandroid.logic.network.repository.SystemRepository
import com.yinhao.wanandroid.other.checkResult
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */
class KnowTreeViewModel : BaseViewModel() {
    private val _systemData = MutableLiveData<UiState<List<KnowledgeTreeBean>>>()
    val systemData: LiveData<UiState<List<KnowledgeTreeBean>>>
        get() = _systemData


    fun getSystemData() {
        viewModelScope.launch {
            val result = SystemRepository.getSystemData()
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
        isSuccess: List<KnowledgeTreeBean>? = null,
        isError: String? = null
    ) {
        val uiState = UiState(
            isLoading, isRefresh, isSuccess, isError
        )
        _systemData.value = uiState
    }
}