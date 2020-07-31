package com.yinhao.wanandroid.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author:  SHIGUANG
 * date:    2018/8/28
 * version: v1.0
 * ### description: 用户记录表
 */
@Entity(tableName = "user")
data class User constructor(@ColumnInfo(name = "username") var username: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "nick_name")
    var nikeName: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "public_name")
    var publicName: String? = null

}
