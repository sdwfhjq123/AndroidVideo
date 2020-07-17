package com.yinhao.wanandroid.widget

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import com.yinhao.wanandroid.R
import org.jetbrains.anko.appcompat.v7.coroutines.onMenuItemClick
import org.jetbrains.anko.toast

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

    fun enableHomeAsUp(icon: Float = 1f, up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable(icon)
        toolbar.setNavigationOnClickListener { up() }
    }

    fun enableMenu(id: Int = R.menu.menu_home, up: () -> Unit = {}) {
        toolbar.inflateMenu(id)
        toolbar.onMenuItemClick {
            when (it!!.itemId) {
                R.id.action_share -> toolbar.context.toast("share")
                R.id.action_search -> toolbar.context.toast("search")
                else -> up()
            }
            true
        }
    }

    fun hiddenMenu(redId: Int) {
        toolbar.menu.findItem(redId).isVisible = false
    }

    fun setToolbarMenu(index: Int) {
        toolbar.menu.clear()
        if (index == 1) enableMenu(R.menu.menu_square) else enableMenu()
    }

    private fun createUpDrawable(icon: Float) =
        DrawerArrowDrawable(toolbar.context).apply { progress = icon }

}