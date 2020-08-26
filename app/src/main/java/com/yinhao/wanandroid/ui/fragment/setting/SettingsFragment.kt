package com.yinhao.wanandroid.ui.fragment.setting

import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.ui.setting.SettingsActivity
import com.yinhao.wanandroid.utils.SettingUtil

/**
 * author:  yinhao
 * date:    2020/8/6
 * version: v1.0
 * ### description:
 */

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)

        //通知首页是否显示top文章
        findPreference<CheckBoxPreference>("switch_show_top")!!.setDefaultValue(SettingUtil.getIsShowTopArticle())
        findPreference<CheckBoxPreference>("switch_show_top")!!.setOnPreferenceChangeListener { preference, newValue ->
            // 通知首页刷新数据
            // 延迟发送通知：为了保证刷新数据时 SettingUtil.getIsShowTopArticle() 得到最新的值
            LiveEventBus.get("switch_show_top").post(newValue)
            true
        }

        findPreference<Preference>("auto_nightMode")!!.setOnPreferenceClickListener {
            (context as SettingsActivity).startWithFragment(AutoNightFragment::class.java.name)
            true
        }

    }
}