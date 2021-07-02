package com.ceodev18.listdata.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "hit")
data class Hit(
        val created_at: String?,
        val title: String?,
        @PrimaryKey(autoGenerate = true) val id: Int,
        val url: String?,
        val author: String?,
        val story_title: String?,
        val status: Int = 1
)