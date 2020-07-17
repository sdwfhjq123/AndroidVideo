package com.yinhao.wanandroid.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivityMainBinding
import com.yinhao.wanandroid.other.ConstantValues
import com.yinhao.wanandroid.ui.main.home.HomeFragment
import com.yinhao.wanandroid.ui.main.system.SystemFragment
import com.yinhao.wanandroid.ui.main.nav.WechatFragment
import com.yinhao.wanandroid.ui.main.project.ProjectFragment
import com.yinhao.wanandroid.ui.main.square.SquareFragment
import com.yinhao.wanandroid.ui.signin.SignInActivity
import com.yinhao.wanandroid.widget.ToolbarManager
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

//TODO 广场，floatingactionbar,切换每个tab页toolbar menu的切换，轮播图的优化
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.toolbar }

    private val fragmentList = arrayListOf<Fragment>()
    private val homeFragment by lazy { HomeFragment() }
    private val squareFragment by lazy { SquareFragment() }
    private val wechatFragment by lazy { WechatFragment() }
    private val systemFragment by lazy { SystemFragment() }
    private val projectFragment by lazy { ProjectFragment() }

    private var mTabIndex = 0

    init {
        fragmentList.add(homeFragment)
        fragmentList.add(squareFragment)
        fragmentList.add(wechatFragment)
        fragmentList.add(systemFragment)
        fragmentList.add(projectFragment)
    }

    override fun initViewModel(): MainViewModel =
        ViewModelProvider(this).get(MainViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)

    override fun initView() {
        initToolbar()

        initViewPager()
        viewBinding?.bottomNavView?.setOnNavigationItemSelectedListener(onNavigationItemSelected)

        //navigationView的赋值
        val usernameSp =
            getSharedPreferences(ConstantValues.SP_NAME, Context.MODE_PRIVATE).getString(
                ConstantValues.SP_KEY_USERNAME,
                ""
            )
        val inflateHeaderView = viewBinding?.navView?.getHeaderView(0)
        val tvUsername = inflateHeaderView?.find<TextView>(R.id.tv_username)
        tvUsername?.text = usernameSp
    }

    override fun initData() {
        val usernameSp =
            getSharedPreferences(ConstantValues.SP_NAME, Context.MODE_PRIVATE).getString(
                ConstantValues.SP_KEY_USERNAME,
                ""
            )
        if (usernameSp.isNullOrEmpty()) {
            startActivity<SignInActivity>()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
//        menu.clear()
//        when (mTabIndex) {
//            1 -> menuInflater.inflate(R.menu.menu_square, menu)
//            else -> menuInflater.inflate(R.menu.menu_home, menu)
//        }
//        return super.onPrepareOptionsMenu(menu)
//    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        enableHomeAsUp(0f) {
            viewBinding?.drawerLayout?.open()
        }
        enableMenu()

        val toggle = ActionBarDrawerToggle(
            this, viewBinding?.drawerLayout, toolbar,
            R.string.app_name, R.string.app_name
        )
        toggle.syncState()
        viewBinding?.drawerLayout?.addDrawerListener(toggle)
    }

    private fun initViewPager() {
        viewBinding?.viewPager?.run {
            isUserInputEnabled = false
            offscreenPageLimit = 2
            adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun createFragment(position: Int) = fragmentList[position]

                override fun getItemCount() = fragmentList.size
            }
        }

    }

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_home -> {
                switchFragment(0)
                toolbarTitle = getString(R.string.app_name)
            }
            R.id.nav_square -> {
                switchFragment(1)
                toolbarTitle = getString(R.string.bottom_nav_square)
            }
            R.id.nav_wechat -> {
                switchFragment(2)
                toolbarTitle = getString(R.string.bottom_nav_wechat)
            }
            R.id.nav_system -> {
                switchFragment(3)
                toolbarTitle = getString(R.string.bottom_nav_system)
            }
            R.id.nav_project -> {
                switchFragment(4)
                toolbarTitle = getString(R.string.bottom_nav_project)
            }
        }
        true
    }

    private fun switchFragment(position: Int): Boolean {
        mTabIndex = position
//        invalidateOptionsMenu()
//        if (mTabIndex == 1) {
//            enableMenu(R.menu.menu_square)
//        } else {
//            enableMenu(R.menu.menu_home)
//        }
        setToolbarMenu(mTabIndex)
        viewBinding?.viewPager?.setCurrentItem(position, false)
        return true
    }

}