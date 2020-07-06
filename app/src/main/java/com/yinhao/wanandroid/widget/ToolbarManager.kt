package com.yinhao.wanandroid.widget

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.ui.signin.SignInActivity
import org.jetbrains.anko.appcompat.v7.coroutines.onMenuItemClick
import org.jetbrains.anko.startActivity

/**
 * author:  yinhao
 * date:    2020/7/6
 * version: v1.0
 * ### description:
 */
interface ToolbarManager {
    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun enableHomeAsUp(icon: Float = 1f,up: () -> Unit ) {
        toolbar.navigationIcon = createUpDrawable(icon)
        toolbar.setNavigationOnClickListener { up() }
    }

    fun enableMenu(id: Int = R.menu.menu_home, up: () -> Unit) {
        toolbar.inflateMenu(id)
        toolbar.onMenuItemClick {
            when (it!!.itemId) {
                R.id.action_menu -> toolbar.context.startActivity<SignInActivity>()
                else -> up()
            }
            true
        }
    }

    fun hiddenMenu(redId: Int) {
        toolbar.menu.findItem(redId).isVisible = false
    }

    private fun createUpDrawable(icon: Float = 1f) =
        DrawerArrowDrawable(toolbar.context).apply { progress = icon }

}