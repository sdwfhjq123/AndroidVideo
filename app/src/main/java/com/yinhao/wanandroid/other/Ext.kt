package com.yinhao.wanandroid.other

import android.app.Activity
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.yinhao.wanandroid.model.Result


/**
 * Created by luyao
 * on 2020/3/30 16:19
 */
inline fun <T : Any> Result<T>.checkResult(crossinline onSuccess: (T) -> Unit, crossinline onError: (String?) -> Unit) {
    if (this is Result.Success) {
        onSuccess(data)
    } else if (this is Result.Error) {
        onError(exception.message)
    }
}

inline fun <T : Any> Result<T>.checkSuccess(success: (T) -> Unit) {
    if (this is Result.Success) {
        success(data)
    }
}

fun String.getAgentWeb(
    activity: Activity,
    webContent: ViewGroup,
    layoutParams: ViewGroup.LayoutParams,
    webView: WebView,
    webViewClient: com.just.agentweb.WebViewClient?,
//    webChromeClient: com.just.agentweb.WebChromeClient?,
    indicatorColor: Int
): AgentWeb = AgentWeb.with(activity)//传入Activity or Fragment
    .setAgentWebParent(webContent, 1, layoutParams)//传入AgentWeb 的父控件
    .useDefaultIndicator(indicatorColor, 2)// 使用默认进度条
    .setWebView(webView)
        .setWebViewClient(webViewClient)
//        .setWebChromeClient(webChromeClient)
//        .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
    .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
    .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
    .interceptUnkownUrl()
    .createAgentWeb()//
    .ready()
    .go(this)