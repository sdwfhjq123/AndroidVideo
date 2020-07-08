package com.yinhao.wanandroid.ui.home

import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivityHomeBinding
import com.yinhao.wanandroid.widget.ToolbarManager

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.toolbar }

    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this).get(HomeViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityHomeBinding =
        ActivityHomeBinding.inflate(inflater)

    override fun initWindowFlag() {
    }

    override fun initEvents() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appBarConfiguration)
        viewBinding?.navView?.let { bnv ->
            bnv.setupWithNavController(navController)
            bnv.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_home -> navController.navigate(R.id.homeFragment)
                    R.id.nav_knowledge -> navController.navigate(R.id.knowledgeFragment)
                    R.id.nav_nav -> navController.navigate(R.id.navFragment)
                    R.id.nav_project -> navController.navigate(R.id.projectFragment)
                }
                true
            }
        }
    }

    override fun start() {
    }

}