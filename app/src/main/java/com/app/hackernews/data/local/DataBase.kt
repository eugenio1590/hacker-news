package com.app.hackernews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
