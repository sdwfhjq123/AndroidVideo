package team.fcma.xframe.others

import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import java.util.*

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: 通用函数存放处
 */
object XFrameCommonMethods {

    /**
     * 判断是否需要隐藏键盘
     */
    fun isShouldHideKeyboard(view: View, event: MotionEvent): Boolean {
        if (view is EditText) {
            val location = IntArray(2)
            view.getLocationInWindow(location)
            val top = location[1]
            val left = location[0]
            val right = left + view.getWidth()
            val bottom = top + view.getHeight()
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }

    /**
     * 获取随机rgb颜色值
     */
    fun randomColor(): Int {
        val random = Random()
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        var red = random.nextInt(190)
        var green = random.nextInt(190)
        var blue = random.nextInt(190)
//        if (SettingUtil.getIsNightMode()) {
//            //150-255
//            red = random.nextInt(105) + 150
//            green = random.nextInt(105) + 150
//            blue = random.nextInt(105) + 150
//        }
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue)
    }
}
