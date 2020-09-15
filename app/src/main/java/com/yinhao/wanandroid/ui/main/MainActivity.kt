package com.yinhao.wanandroid.ui.main

import android.view.LayoutInflater
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yinhao.wanandroid.base.BaseVMActivity
import com.yinhao.wanandroid.App
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.databinding.ActivityMainBinding
import com.yinhao.wanandroid.db.entity.User
import com.yinhao.wanandroid.event.LoginEvent
import com.yinhao.wanandroid.model.bean.UserInfoBody
import com.yinhao.wanandroid.other.ConstantValues
import com.yinhao.wanandroid.ui.common.CommonActivity
import com.yinhao.wanandroid.ui.fragment.home.HomeFragment
import com.yinhao.wanandroid.ui.fragment.system.SystemFragment
import com.yinhao.wanandroid.ui.fragment.wechat.WechatFragment
import com.yinhao.wanandroid.ui.fragment.project.ProjectFragment
import com.yinhao.wanandroid.ui.fragment.square.SquareFragment
import com.yinhao.wanandroid.ui.login.LoginActivity
import com.yinhao.wanandroid.ui.score.ScoreActivity
import com.yinhao.wanandroid.ui.setting.SettingsActivity
import com.yinhao.wanandroid.utils.SettingUtil
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

//TODO 把颜色xml和其他项目同步
class MainActivity : BaseVMActivity<MainViewModel, ActivityMainBinding>() {
    private val toolbar: Toolbar by lazy { viewBinding!!.appbar.toolbar }

    private val fragmentList = arrayListOf<Fragment>()
    private val homeFragment by lazy { HomeFragment() }
    private val squareFragment by lazy { SquareFragment() }
    private val wechatFragment by lazy { WechatFragment() }
    private val systemFragment by lazy { SystemFragment() }
    private val projectFragment by lazy { ProjectFragment() }

    private var mTabIndex = 0
    private var userInfo: UserInfoBody? = null
    private var user: User? = null

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

        initFragment()

        viewObserver()
    }

    override fun initData() {
        viewModel.getUser()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun viewObserver() {
        LiveEventBus.get("login", LoginEvent::class.java)
            .observe(this, Observer {
                if (it.isLogin) {
                    viewModel.getUser()
                } else {
                    initNavView()
                }
            })

        viewModel.user.observe(this) { user ->
            this@MainActivity.user = user
            initNavView()
            if (this@MainActivity.user != null) viewModel.getUserScore()
        }

        viewModel.userScore.observe(this) { score ->
            userInfo = score
            initNavView()
        }
    }

    private fun initNavView() {
        viewBinding?.navView?.run {
            val navUsername = getHeaderView(0).find<TextView>(R.id.tv_username)

            navUsername.let { username ->
                username.text =
                    if (prefIsLogin) user?.username else getString(R.string.go_login)
                username.setOnClickListener {
                    if (!prefIsLogin) {
                        toast(getString(R.string.go_login))
                        startActivity<LoginActivity>()
                    }
                }
            }

            val navUserGrade = getHeaderView(0).find<TextView>(R.id.tv_user_grade)
            navUserGrade.let { grade ->
                grade.text = "${userInfo?.coinCount ?: resources.getString(R.string.nav_line_2)}"
            }

            val navUserRank = getHeaderView(0).find<TextView>(R.id.tv_user_rank)
            navUserRank.let { rank ->
                rank.text = "${userInfo?.rank ?: resources.getString(R.string.nav_line_2)}"
            }

            menu.findItem(R.id.nav_logout).isVisible = prefIsLogin

            setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_score -> {
                        goLogin { startActivity<ScoreActivity>() }
                    }
                    R.id.nav_collect -> {
                        goLogin { goCommonActivity(ConstantValues.Type.COLLECT_TYPE_KEY) }
                    }
                    R.id.nav_share -> {
                        goLogin { goCommonActivity(ConstantValues.Type.COLLECT_TYPE_KEY) }
                    }
                    R.id.nav_setting -> {
                        startActivity<SettingsActivity>()
                    }
                    //R.id.nav_about_us -> {
                    //    goCommonActivity(Constant.Type.ABOUT_US_TYPE_KEY)
                    //}
                    R.id.nav_logout -> {
                        getIsLogoutAlert { logout() }.show()

                    }
                    R.id.nav_night_mode -> {
                        if (SettingUtil.getIsNightMode()) {
                            SettingUtil.setIsNightMode(false)
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        } else {
                            SettingUtil.setIsNightMode(true)
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        }
                        window.setWindowAnimations(R.style.WindowAnimationFadeInOut)
                        recreate()
                    }
                    R.id.nav_todo -> {
//                        if (prefIsLogin) {
//                            Intent(this@MainActivity, TodoActivity::class.java).run {
//                                startActivity(this)
//                            }
//                        } else {
//                            showToast(resources.getString(R.string.login_tint))
//                            goLogin()
//                        }
                    }
                }
                true
            }
        }
    }

    private fun initFragment() {
        viewBinding?.bottomNavView?.setOnNavigationItemSelectedListener(onNavigationItemSelected)
    }

    private fun initToolbar() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        viewBinding?.drawerLayout?.run {
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                this,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }

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
            }
            R.id.nav_square -> {
                switchFragment(1)
            }
            R.id.nav_wechat -> {
                switchFragment(2)
            }
            R.id.nav_system -> {
                switchFragment(3)
            }
            R.id.nav_project -> {
                switchFragment(4)
            }
        }
        true
    }

    private fun switchFragment(position: Int): Boolean {
        mTabIndex = position
        viewBinding?.viewPager?.setCurrentItem(position, false)

        when (position) {
            0 -> toolbar.title = getString(R.string.app_name)
            1 -> toolbar.title = getString(R.string.bottom_nav_square)
            2 -> toolbar.title = getString(R.string.bottom_nav_wechat)
            3 -> toolbar.title = getString(R.string.bottom_nav_system)
            4 -> toolbar.title = getString(R.string.bottom_nav_project)
        }

        return true
    }

    private fun goLogin(block: (() -> Unit)?) {
        if (prefIsLogin) {
            block?.invoke()
        } else {
            getNotSignedAlert {
                toast(resources.getString(R.string.go_login))
                startActivity<LoginActivity>()
            }.show()
        }
    }

    private fun logout() {
        viewModel.logout()
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.instance)).clear()
        LiveEventBus.get("login", Boolean::class.java).post(false)
    }

    private fun goCommonActivity(type: String) {
        startActivity<CommonActivity>(
            CommonActivity.TYPE to type
        )
    }

}