package com.yinhao.wanandroid.ui.common

import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.yinhao.wanandroid.base.BaseVMActivity
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivityCommonBinding
import com.yinhao.wanandroid.ui.fragment.collection.CollectionFragment
import com.yinhao.wanandroid.widget.ToolbarManager

//TODO 点击html跳转到外部浏览器
class CommonActivity : BaseVMActivity<CommonModel, ActivityCommonBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.appbar.toolbar }

    companion object {
        const val TYPE = "com.yinhao.wanandroid.ui.common.CommonActivity.type"
    }

    override fun initViewModel(): CommonModel = ViewModelProvider(this).get(CommonModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityCommonBinding =
        ActivityCommonBinding.inflate(layoutInflater)

    override fun initView() {
        initToolbar()

        // 添加碎片
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, CollectionFragment.newInstance())
            .commit()
    }


    override fun initData() {

    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbarTitle = resources.getString(R.string.nav_my_collect)
        enableHomeAsUp(1f) { finish() }
        enableMenu()
    }

}