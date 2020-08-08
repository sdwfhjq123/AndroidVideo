package com.yinhao.wanandroid.ui.common

import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.yinhao.commonmodule.base.base.BaseVMActivity
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivityCommonBinding
import com.yinhao.wanandroid.ui.fragment.collection.CollectionFragment
import com.yinhao.wanandroid.widget.ToolbarManager
import kotlinx.android.synthetic.main.activity_common.view.*

class CommonActivity : BaseVMActivity<CommonModel, ActivityCommonBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.toolbar }

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