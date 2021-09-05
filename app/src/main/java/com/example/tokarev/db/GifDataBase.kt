package com.example.tokarev.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LatestEntity::class], version = 1, exportSchema = false)
abstract class GifDataBase: RoomDatabase() {

    abstract fun gifDao(): GifDao

    companion object {
        @Volatile
        private var INSTANCE: GifDataBase? = null

        fun getDatabase(context: Context): GifDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GifDataBase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
