package com.ceodev18.listdata.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ceodev18.listdata.data.entities.Hit


@Dao
interface HitDAO {
    @Query("SELECT * FROM hit where status IS NULL")
    fun getAllHits(): LiveData<List<Hit>>

    @Query("SELECT * FROM hit WHERE objectID = :id")
    fun getHit(id: Int): LiveData<Hit>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(hits: List<Hit>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hit: Hit)

    @Delete
    fun delete(hit: Hit)

    @Query("UPDATE hit SET status = 0 WHERE objectID = :id ")
    fun softDelete(id: Int)


}