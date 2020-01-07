package com.example.kodein.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kodein.model.Post
import com.example.kodein.model.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}