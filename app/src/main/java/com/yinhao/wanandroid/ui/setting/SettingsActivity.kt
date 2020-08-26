package com.yinhao.wanandroid.ui.setting

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceActivity.*
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentTransaction
import com.yinhao.commonmodule.base.base.BaseVMActivity
import com.yinhao.commonmodule.base.base.BaseViewModel
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivitySettingsBinding
import com.yinhao.wanandroid.ui.fragment.setting.SettingsFragment
import com.yinhao.wanandroid.widget.ToolbarManager

class SettingsActivity : BaseVMActivity<BaseViewModel, ActivitySettingsBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.appbar.toolbar }

    override fun initViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initViewBinging(inflater: LayoutInflater): ActivitySettingsBinding =
        ActivitySettingsBinding.inflate(layoutInflater)

    override fun initView() {
        initToolbar()

        val initFragment: String = intent.getStringExtra(EXTRA_SHOW_FRAGMENT) ?: ""
        val initArguments: Bundle = intent.getBundleExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS) ?: Bundle()

        if (initFragment.isEmpty()) {
            setupFragment(SettingsFragment::class.java.name, initArguments)
        } else {
            setupFragment(initFragment, initArguments)
        }

    }

    private fun initToolbar() {
        val initTitle: String = intent.getStringExtra(EXTRA_SHOW_FRAGMENT_TITLE)
            ?: resources.getString(R.string.setting)
        toolbarTitle = initTitle
        enableHomeAsUp { finish() }
    }

    override fun initData() {

    }

    private fun setupFragment(fragmentName: String, args: Bundle) {
        val fragment = FragmentFactory().instantiate(classLoader, fragmentName)
        fragment.arguments = args
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.container, fragment)
        transaction.commitAllowingStateLoss()
    }

    private fun onBuildStartFragmentIntent(fragmentName: String, args: Bundle?, title: String?)
            : Intent {
        return Intent(this, javaClass).apply {
            putExtra(EXTRA_SHOW_FRAGMENT, fragmentName)
            putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, args)
            putExtra(EXTRA_SHOW_FRAGMENT_TITLE, title)
        }
    }

    fun startWithFragment(
        fragmentName: String, args: Bundle? = null,
        resultTo: Fragment? = null, resultRequestCode: Int = 0, title: String? = null
    ) {
        val intent = onBuildStartFragmentIntent(fragmentName, args, title)
        if (resultTo == null) {
            startActivity(intent)
        } else {
            resultTo.startActivityForResult(intent, resultRequestCode)
        }
    }

}