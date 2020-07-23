package com.yinhao.wanandroid.ui.main.system.nav

import android.content.Context
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yinhao.verticaltablayout.adapter.TabAdapter
import com.yinhao.verticaltablayout.widget.ITabView
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.logic.model.bean.NavigationBean

/**
 * author:  yinhao
 * date:    2020/7/22
 * version: v1.0
 * ### description:
 */
class NavigationTabAdapter(context: Context?, list: List<NavigationBean>) : TabAdapter {
    private var context: Context = context!!
    private var list = mutableListOf<NavigationBean>()

    init {
        this.list = list as MutableList<NavigationBean>
    }

    override fun getIcon(position: Int): ITabView.TabIcon? = null


    override fun getBadge(position: Int): ITabView.TabBadge? = null

    override fun getBackground(position: Int): Int = -1

    override fun getTitle(position: Int): ITabView.TabTitle {
        return ITabView.TabTitle.Builder()
            .setContent(list[position].name)
            .setTextColor(
                ContextCompat.getColor(context, R.color.colorAccent),
                ContextCompat.getColor(context, R.color.xf_Grey500)
            )
            .build()
    }

    override fun getCount(): Int = list.size
}