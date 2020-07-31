package com.yinhao.wanandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yinhao.wanandroid.App
import com.yinhao.wanandroid.db.entity.User
import com.yinhao.wanandroid.db.dao.UserDao

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: 本地数据库
 */
@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {

    /**
     * ### AppRecord的DAO
     */
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(): AppDatabase {
            instance?.let { return it }
            return Room.databaseBuilder(
                App.instance,
                AppDatabase::class.java,
                "app_database"
            )
//                .allowMainThreadQueries()//测试专用
                .fallbackToDestructiveMigration()//测试专用
                .build().apply { instance = this }
        }
    }
}
