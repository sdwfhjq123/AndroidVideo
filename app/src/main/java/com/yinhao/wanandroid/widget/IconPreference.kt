package com.yinhao.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.utils.SettingUtil
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by chenxz on 2018/6/13.
 */
class IconPreference(context: Context, attrs: AttributeSet) : Preference(context, attrs) {

    private var circleImageView: CircleImageView? = null

    init {

        widgetLayoutResource = R.layout.item_icon_preference_preview
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        val color = SettingUtil.getColor()
        circleImageView = holder?.itemView?.findViewById(R.id.iv_preview)
        circleImageView!!.setBackgroundColor(color)
    }

    fun setView() {
        val color = SettingUtil.getColor()
        circleImageView!!.setBackgroundColor(color)
    }
}