package com.yinhao.wanandroid.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yinhao.commonmodule.base.repository.dao.BaseDao
import com.yinhao.wanandroid.db.entity.User

/**
 * author:      SHIGUANG
 * date:        2018/9/28
 * version:     v1.0
 * ### description: 为AppRecord提供的Dao
 */
@Dao
interface UserDao : BaseDao<User> {

    /**
     * ### 获取全部
     */
    @Query("select * from user")
    suspend fun getAll(): List<User>

    //删全部
    @Query("DELETE FROM user")
    suspend fun deleteUser()

    @Insert
    suspend fun insertUser(user: User)
}
