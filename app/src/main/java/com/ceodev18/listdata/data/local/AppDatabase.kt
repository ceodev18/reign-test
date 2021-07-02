package com.ceodev18.listdata.data.local

import android.content.Context
import androidx.room.*
import com.ceodev18.listdata.data.entities.Hit

@Database(entities = [Hit::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hitDAO(): HitDAO

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
                instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
                Room.databaseBuilder(appContext, AppDatabase::class.java, "hits")
                        .fallbackToDestructiveMigration()
                        .build()
    }

}