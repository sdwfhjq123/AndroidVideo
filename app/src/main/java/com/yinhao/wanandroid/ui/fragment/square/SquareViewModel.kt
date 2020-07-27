package com.yinhao.wanandroid.ui.fragment.square

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.logic.model.bean.ArticleBean
import com.yinhao.wanandroid.logic.network.repository.SquareRepository
import com.yinhao.wanandroid.other.checkResult
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/17
 * version: v1.0
 * ### description:
 */
class SquareViewModel : BaseViewModel() {

    private val _uiState = MutableLiveData<BaseUiModel<List<ArticleBean>>>()

    val uiState: LiveData<BaseUiModel<List<ArticleBean>>>
        get() = _uiState

    private var pageNum = 1

    fun getSquareList(isRefresh: Boolean) {
        if (isRefresh) pageNum = 1

        emitArticleUiState(showLoading = true)
        viewModelScope.launch {
            val result = SquareRepository.getSquareList(pageNum)
            result.checkResult(
                onSuccess = {
                    pageNum++
                    emitArticleUiState(
                        showLoading = false,
                        showSuccess = it.datas,
                        isRefresh = isRefresh
                    )
                },
                onError = {
                    emitArticleUiState(showLoading = false, showError = it)
                }
            )
        }
    }


    private fun emitArticleUiState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: List<ArticleBean>? = null,
        showEnd: Boolean = false,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null
    ) {
        val uiModel = BaseUiModel(
            showLoading,
            showError,
            showSuccess,
            showEnd,
            isRefresh,
            needLogin
        )
        _uiState.value = uiModel
    }

}