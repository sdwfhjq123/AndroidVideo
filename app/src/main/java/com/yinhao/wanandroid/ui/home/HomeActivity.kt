package com.yinhao.wanandroid.ui.home

import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivityHomeBinding
import com.yinhao.wanandroid.widget.ToolbarManager

//TODO 考虑是采用github的方式还是简书 https://www.jianshu.com/p/41d0e33bb674
class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.toolbar }

    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this).get(HomeViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityHomeBinding =
        ActivityHomeBinding.inflate(inflater)

    override fun initWindowFlag() {
    }

    override fun initEvents() {
        setSupportActionBar(toolbar)
        enableHomeAsUp(1f) {
//            viewBinding?.drawerLayout.star
        }
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration =
            AppBarConfiguration(
                setOf(R.id.nav_home, R.id.nav_knowledge, R.id.nav_nav, R.id.nav_project),
                viewBinding?.drawerLayout
            )

        setupActionBarWithNavController(navController, appBarConfiguration)
        viewBinding?.navView?.apply {
            setupWithNavController(navController)
            setOnNavigationItemSelectedListener {
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