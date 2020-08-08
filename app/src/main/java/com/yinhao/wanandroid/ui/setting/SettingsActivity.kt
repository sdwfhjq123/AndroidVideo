package com.yinhao.wanandroid.ui.setting

import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import com.yinhao.commonmodule.base.base.BaseVMActivity
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivitySettingsBinding
import com.yinhao.wanandroid.ui.fragment.setting.SettingsFragment
import com.yinhao.wanandroid.widget.ToolbarManager

//TODO  不用navigation了， 看看人家是怎么实现跳转的
class SettingsActivity : BaseVMActivity<BaseViewModel, ActivitySettingsBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.toolbar }

    override fun initViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initViewBinging(inflater: LayoutInflater): ActivitySettingsBinding =
        ActivitySettingsBinding.inflate(layoutInflater)

    override fun initView() {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, SettingsFragment())
//            .commit()

        initToolbar()

    }

    private fun initToolbar() {
        toolbarTitle = resources.getString(R.string.setting)
        enableHomeAsUp { finish() }
    }

    override fun initData() {
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.container).navigateUp()
    }

}