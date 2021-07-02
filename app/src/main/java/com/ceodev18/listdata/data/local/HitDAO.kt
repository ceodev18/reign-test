package com.ceodev18.listdata.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceodev18.listdata.data.entities.Hit


@Dao
interface HitDAO {
    @Query("SELECT * FROM hit")
    fun getAllHits() : LiveData<List<Hit>>

    @Query("SELECT * FROM hit WHERE id = :id")
    fun getHit(id: Int): LiveData<Hit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hits: List<Hit>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hit: Hit)


}