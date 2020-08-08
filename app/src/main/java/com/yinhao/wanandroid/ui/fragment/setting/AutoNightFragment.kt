package com.yinhao.wanandroid.ui.fragment.setting

import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceFragmentCompat
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yinhao.wanandroid.R

/**
 * author:  yinhao
 * date:    2020/8/6
 * version: v1.0
 * ### description:
 */

class AutoNightFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.autonight_preferences, rootKey)

    }
}