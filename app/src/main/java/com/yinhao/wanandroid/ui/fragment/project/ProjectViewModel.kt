package com.yinhao.wanandroid.ui.fragment.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.model.bean.ProjectTreeBean
import com.yinhao.wanandroid.network.repository.ProjectRepository
import com.yinhao.wanandroid.other.checkResult
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/7/25
 * version: v1.0
 * ### description:
 */
class ProjectViewModel : BaseViewModel() {
    private var _tabUiState = MutableLiveData<UiState<List<ProjectTreeBean>>>()
    val tabUiState: LiveData<UiState<List<ProjectTreeBean>>>
        get() = _tabUiState

    fun getWxTabList() {
        viewModelScope.launch {
            val result = ProjectRepository.getProjectCategoryList()
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
        isSuccess: List<ProjectTreeBean>? = null,
        isError: String? = null
    ) {
        val uiModel = UiState(
            isLoading, isRefresh, isSuccess, isError
        )
        _tabUiState.value = uiModel
    }
}