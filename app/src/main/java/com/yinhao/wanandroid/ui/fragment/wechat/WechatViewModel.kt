package com.yinhao.wanandroid.ui.fragment.wechat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.logic.model.bean.WXChapterBean
import com.yinhao.wanandroid.logic.network.repository.WechatRepository
import com.yinhao.wanandroid.other.checkResult
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/20
 * version: v1.0
 * ### description:
 */
class WechatViewModel : BaseViewModel() {
    private var _tabUiState = MutableLiveData<UiState<List<WXChapterBean>>>()
    val tabUiState: LiveData<UiState<List<WXChapterBean>>>
        get() = _tabUiState

    fun getWxTabList() {
        viewModelScope.launch {
            val result = WechatRepository.getWxTabList()
            result.checkResult(
                onSuccess = {
                    emitArticleUiState(isSuccess = it)
                },
                onError = {
                    emitArticleUiState(isError = it)
                }
            )
        }
    }

    private fun emitArticleUiState(
        isLoading: Boolean = false,
        isRefresh: Boolean = false,
        isSuccess: List<WXChapterBean>? = null,
        isError: String? = null
    ) {
        val uiModel = UiState(
            isLoading, isRefresh, isSuccess, isError
        )
        _tabUiState.value = uiModel
    }
}