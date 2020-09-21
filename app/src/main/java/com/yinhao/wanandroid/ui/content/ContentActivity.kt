package com.yinhao.wanandroid.ui.content

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.NestedScrollAgentWebView
import com.yinhao.wanandroid.base.BaseVMActivity
import com.yinhao.wanandroid.databinding.ActivityContentBinding
import com.yinhao.wanandroid.other.getAgentWeb
import com.yinhao.wanandroid.webclient.WebClientFactory
import com.yinhao.wanandroid.widget.ToolbarManager
import org.jetbrains.anko.startActivity


class ContentActivity : BaseVMActivity<ContentModel, ActivityContentBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.appbar.toolbar }

    private var mAgentWeb: AgentWeb? = null

    private var shareTitle: String = ""
    private var shareUrl: String = ""
    private var shareId: Int = -1

    companion object {
        fun actionStart(context: Activity?, id: Int, title: String, link: String) {
            context?.startActivity<ContentActivity>(
                "title" to title,
                "id" to id,
                "link" to link
            )
        }
    }

    override fun initViewModel(): ContentModel =
        ViewModelProvider(this).get(ContentModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityContentBinding =
        ActivityContentBinding.inflate(layoutInflater)

    override fun initView() {
        intent.extras?.let {
            shareId = it.getInt("id", -1)
            shareTitle = it.getString("title", "")
            shareUrl = it.getString("link", "")
        }
        initToolbar()

        initWebView()
    }

    override fun initData() {

    }

    private fun initToolbar() {
        toolbarTitle = shareTitle
        enableHomeAsUp { finish() }
    }

    /**
     * 初始化 WebView
     */
    private fun initWebView() {

        val webView = NestedScrollAgentWebView(this)

        val layoutParams = CoordinatorLayout.LayoutParams(-1, -1)
        layoutParams.behavior = AppBarLayout.ScrollingViewBehavior()

        mAgentWeb = shareUrl.getAgentWeb(
            this,
            viewBinding?.coordinator!!,
            layoutParams,
            webView,
            WebClientFactory.create(shareUrl),
//            mWebChromeClient,
            mThemeColor
        )

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //    WebView.setWebContentsDebuggingEnabled(true)
        //}

        mAgentWeb?.webCreator?.webView?.apply {
            overScrollMode = WebView.OVER_SCROLL_NEVER
            settings.domStorageEnabled = true
            settings.javaScriptEnabled = true
            settings.loadsImagesAutomatically = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }
    }

    private val mWebChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
//            viewBinding?.appbar?.toolbar?.title = title
        }
    }

    override fun onPause() {
        mAgentWeb!!.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb!!.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb!!.webLifeCycle.onDestroy()
        super.onDestroy()
    }
}