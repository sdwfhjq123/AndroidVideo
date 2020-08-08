package com.yinhao.wanandroid.ui.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.model.bean.UserScoreBean
import com.yinhao.wanandroid.network.repository.UserRepository
import com.yinhao.wanandroid.other.checkResult
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/8/3
 * version: v1.0
 * ### description:
 */
class ScoreViewModel : BaseViewModel() {
    private val _uiState = MutableLiveData<BaseUiModel<List<UserScoreBean>>>()
    val uiState: LiveData<BaseUiModel<List<UserScoreBean>>>
        get() = _uiState

    var page = 1

    fun getScoreViewList(isRefresh: Boolean) {
        viewModelScope.launch {
            if (isRefresh) {
                page = 1
            }
            emitUiState(true)
            val result = UserRepository.getUserScoreList(page)
            result.checkResult({
                if (it.offset >= it.total) {
                    emitUiState(showLoading = false, showEnd = true)
                    return@checkResult
                }
                page++
                emitUiState(
                    showLoading = false,
                    showSuccess = it.datas
                )
            }, {
                Log.i("ScoreViewModel", it)
            })
        }
    }

    private fun emitUiState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: List<UserScoreBean>? = null,
        showEnd: Boolean = false,
        isRefresh: Boolean = false
    ) {
        val uiModel = BaseUiModel(
            showLoading,
            showError,
            showSuccess,
            showEnd,
            isRefresh,
            false
        )
        _uiState.value = uiModel
    }
}