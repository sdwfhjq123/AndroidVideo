package com.yinhao.wanandroid.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yinhao.commonmodule.base.base.BaseActivity
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivityHomeBinding
import com.yinhao.wanandroid.other.ConstantValues
import com.yinhao.wanandroid.ui.home.home.HomeFragment
import com.yinhao.wanandroid.ui.home.knowledge.KnowledgeFragment
import com.yinhao.wanandroid.ui.home.nav.NavFragment
import com.yinhao.wanandroid.ui.home.project.ProjectFragment
import com.yinhao.wanandroid.ui.signin.SignInActivity
import com.yinhao.wanandroid.widget.ToolbarManager
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(), ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.toolbar }

    private val fragmentList = arrayListOf<Fragment>()
    private val homeFragment by lazy { HomeFragment() }
    private val knowledgeFragment by lazy { KnowledgeFragment() }
    private val navFragment by lazy { NavFragment() }
    private val projectFragment by lazy { ProjectFragment() }

    init {
        fragmentList.add(homeFragment)
        fragmentList.add(knowledgeFragment)
        fragmentList.add(navFragment)
        fragmentList.add(projectFragment)
    }

    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this).get(HomeViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityHomeBinding =
        ActivityHomeBinding.inflate(inflater)

    override fun initView() {
        setSupportActionBar(toolbar)
        enableHomeAsUp(0f) {
            viewBinding?.drawerLayout?.open()
        }

        initViewPager()
        viewBinding?.bottomNavView?.setOnNavigationItemSelectedListener(onNavigationItemSelected)

        val toggle = ActionBarDrawerToggle(
            this, viewBinding?.drawerLayout, toolbar,
            R.string.app_name, R.string.app_name
        )
        toggle.syncState()
        viewBinding?.drawerLayout?.addDrawerListener(toggle)

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

    private fun initViewPager() {
        viewBinding?.viewPager?.run {
            isUserInputEnabled = false
            offscreenPageLimit = 2
            adapter = object : FragmentStateAdapter(this@HomeActivity) {
                override fun createFragment(position: Int) = fragmentList[position]

                override fun getItemCount() = fragmentList.size
            }
        }
    }

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_home -> {
                switchFragment(0)
                toolbarTitle = getString(R.string.bottom_nav_home)
            }
            R.id.nav_knowledge -> {
                switchFragment(1)
                toolbarTitle = getString(R.string.bottom_nav_knowledge)
            }
            R.id.nav_nav -> {
                switchFragment(2)
                toolbarTitle = getString(R.string.bottom_nav_nav)
            }
            R.id.nav_project -> {
                switchFragment(3)
                toolbarTitle = getString(R.string.bottom_nav_project)
            }
        }
        true
    }

    private fun switchFragment(position: Int): Boolean {
        viewBinding?.viewPager?.setCurrentItem(position, false)
        return true
    }

}