package com.example.todaymemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * データベースの本体（総本山）
 */
@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // 窓口（DAO）を使えるように定義
    abstract fun taskDao(): TaskDao

    companion object {
        // インスタンス（本体）を一つだけ保持する変数
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * データベースの本体を取得する関数
         * まだ作られていなければ作り、既にあればそれを返す（シングルトン）
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "today_memo_db" // データベースファイルの名前
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}