package com.yinhao.wanandroid.ui.fragment.setting

import android.graphics.Color
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.ColorPalette
import com.afollestad.materialdialogs.color.colorChooser
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.yinhao.wanandroid.utils.CacheDataUtil
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yinhao.wanandroid.App
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.event.ColorEvent
import com.yinhao.wanandroid.ui.setting.SettingsActivity
import com.yinhao.wanandroid.utils.SettingUtil
import com.yinhao.wanandroid.widget.IconPreference
import org.jetbrains.anko.toast

/**
 * author:  yinhao
 * date:    2020/8/6
 * version: v1.0
 * ### description:
 */

class SettingsFragment : PreferenceFragmentCompat() {
    val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE)

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)

        setDefaultText()

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

        findPreference<IconPreference>("color")!!.setOnPreferenceClickListener {
            MaterialDialog(requireContext()).show {
                title(R.string.choose_theme_color)
                colorChooser(
                    ColorPalette.Primary,
                    ColorPalette.PrimarySub
                ) { _, color ->
                    SettingUtil.setColor(color)
                    findPreference<IconPreference>("color")!!.setView()
                    LiveEventBus.get("color_changed", ColorEvent::class.java)
                        .post(ColorEvent(true))
                }
                positiveButton(R.string.done)
                negativeButton(R.string.cancel)
                lifecycleOwner(this@SettingsFragment)
            }

            false
        }

        findPreference<Preference>("clearCache")!!.onPreferenceClickListener =
            Preference.OnPreferenceClickListener {
                CacheDataUtil.clearAllCache(App.instance)
                context?.toast(getString(R.string.clear_cache_successfully))
                setDefaultText()
                false
            }

    }

    private fun setDefaultText() {
        try {
            findPreference<Preference>("clearCache")!!.summary =
                CacheDataUtil.getTotalCacheSize(App.instance)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}