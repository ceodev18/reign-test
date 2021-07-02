package com.ceodev18.listdata.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "hit")
data class Hit(
        val created_at: String?,
        val title: String?,
        @PrimaryKey(autoGenerate = false) val objectID: Int,
        val url: String?,
        val story_url: String?,
        val author: String?,
        val story_title: String?,
        @ColumnInfo(name = "status", defaultValue = "1")val status: Int?
)