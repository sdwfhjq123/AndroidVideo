package com.yinhao.wanandroid.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.logic.model.bean.BannerBean
import com.yinhao.wanandroid.logic.network.repository.HomeRepository
import com.yinhao.wanandroid.other.checkSuccess
import com.yinhao.wanandroid.logic.model.Result
import com.yinhao.wanandroid.logic.model.bean.ArticleBean
import kotlinx.coroutines.async

/**
 * author:  yinhao
 * date:    2020/7/11
 * version: v1.0
 * ### description:
 */
class HomeViewModel : BaseViewModel() {
    private val _bannerData = MutableLiveData<List<BannerBean>>()
    private val _uiState = MutableLiveData<ArticleUiModel>()

    val bannerData: LiveData<List<BannerBean>>
        get() = _bannerData
    val uiState: LiveData<ArticleUiModel>
        get() = _uiState

    private var pageNum = 1

    fun getHomeBanner() {
        launchOnUI {
            val result = HomeRepository.getHomeBanner()
            result.checkSuccess {
                _bannerData.value = it
            }
        }
    }

    fun getArticleList(isRefresh: Boolean) {
        launchOnUI {
            if (isRefresh) {
                pageNum = 1
            }
            emitArticleUiState(true)
            val deferred = async { HomeRepository.getArticleList(pageNum) }
            val result = deferred.await()

            if (isRefresh) {
                val deferredTop = async { HomeRepository.getTopArticleList() }
                val topResult = deferredTop.await()

                if (result is Result.Success && topResult is Result.Success) {
                    val articleList = result.data.datas
                    val topArticleList = topResult.data
                    topArticleList.forEach {
                        it.top = 1
                    }
                    pageNum++
                    val plus = topArticleList.plus(articleList)
                    emitArticleUiState(
                        showLoading = false,
                        showSuccess = plus,
                        isRefresh = true
                    )

                } else if (result is Result.Error || topResult is Result.Error) {
                    emitArticleUiState(showLoading = false, showError = "获取失败")
                }
            } else {
                if (result is Result.Success) {
                    val articleList = result.data
                    if (articleList.offset >= articleList.total) {
                        emitArticleUiState(showLoading = false, showEnd = true)
                        return@launchOnUI
                    }
                    pageNum++
                    emitArticleUiState(
                        showLoading = false,
                        showSuccess = articleList.datas
                    )

                } else if (result is Result.Error) {
                    emitArticleUiState(showLoading = false, showError = result.exception.message)
                }
            }

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
        val uiModel = ArticleUiModel(
            showLoading,
            showError,
            showSuccess,
            showEnd,
            isRefresh,
            needLogin
        )
        _uiState.value = uiModel
    }

    data class ArticleUiModel(
        val showLoading: Boolean,
        val showError: String?,
        val showSuccess: List<ArticleBean>?,
        val showEnd: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null
    )
}