package com.yinhao.wanandroid.ui.fragment.setting

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.utils.SettingUtil

/**
 * author:  yinhao
 * date:    2020/8/6
 * version: v1.0
 * ### description:
 */

class AutoNightFragment : PreferenceFragmentCompat() {
    private lateinit var nightStartHour: String
    private lateinit var nightStartMinute: String
    private lateinit var dayStartHour: String
    private lateinit var dayStartMinute: String

    private lateinit var autoNightPref: Preference
    private lateinit var autoDayPref: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.autonight_preferences, rootKey)
        setHasOptionsMenu(true)

        autoNightPref = findPreference("auto_night")!!
        autoDayPref = findPreference("auto_day")!!

        setDefaultText()

        autoNightPref.setOnPreferenceClickListener {
            val dialog = TimePickerDialog(
                context,
                { view, hour, minute ->
                    SettingUtil.setNightStartHour(if (hour > 9) hour.toString() else "0$hour")
                    SettingUtil.setNightStartMinute(if (minute > 9) minute.toString() else "0$minute")
                    setDefaultText()
                },
                dayStartHour.toInt(),
                dayStartMinute.toInt(),
                true
            )
            dialog.show()
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(R.string.done)
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText(R.string.cancel)
            false
        }

        autoDayPref.setOnPreferenceClickListener {
            val dialog = TimePickerDialog(activity, { _, hour, minute ->
                SettingUtil.setDayStartHour(if (hour > 9) hour.toString() else "0$hour")
                SettingUtil.setDayStartMinute(if (minute > 9) minute.toString() else "0$minute")
                setDefaultText()
            }, dayStartHour.toInt(), dayStartMinute.toInt(), true)
            dialog.show()
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(R.string.done)
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText(R.string.cancel)
            false
        }
    }

    private fun setDefaultText() {

        nightStartHour = SettingUtil.getNightStartHour()!!
        nightStartMinute = SettingUtil.getNightStartMinute()!!
        dayStartHour = SettingUtil.getDayStartHour()!!
        dayStartMinute = SettingUtil.getDayStartMinute()!!

        autoNightPref.summary = "$nightStartHour:$nightStartMinute"
        autoDayPref.summary = "$dayStartHour:$dayStartMinute"
    }
}