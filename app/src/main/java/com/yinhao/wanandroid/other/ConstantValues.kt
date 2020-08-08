package com.yinhao.wanandroid.other

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: 静态常量存放处
 */

object ConstantValues {
    const val DEBUG = true
    const val PREF_USER_RECORD = "PREF_USER_RECORD"

    const val BASE_URL = "https://www.wanandroid.com/"

    const val USERNAME_KEY = "username"
    const val LOGIN_KEY = "login"
    const val PASSWORD_KEY = "password"
    const val TOKEN_KEY = "token"
    const val HAS_NETWORK_KEY = "has_network"

    object Type {
        const val COLLECT_TYPE_KEY = "collect_type"
        const val ABOUT_US_TYPE_KEY = "about_us_type_key"
        const val SETTING_TYPE_KEY = "setting_type_key"
        const val SEARCH_TYPE_KEY = "search_type_key"
        const val ADD_TODO_TYPE_KEY = "add_todo_type_key"
        const val SEE_TODO_TYPE_KEY = "see_todo_type_key"
        const val EDIT_TODO_TYPE_KEY = "edit_todo_type_key"
        const val SHARE_ARTICLE_TYPE_KEY = "share_article_type_key"
        const val SCAN_QR_CODE_TYPE_KEY = "scan_qr_code_type_key"
    }
}
