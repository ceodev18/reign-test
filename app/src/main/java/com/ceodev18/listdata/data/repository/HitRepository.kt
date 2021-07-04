package com.ceodev18.listdata.data.repository


import com.ceodev18.listdata.data.entities.Hit
import com.ceodev18.listdata.data.local.HitDAO
import com.ceodev18.listdata.data.remote.HitRemoteDataSource
import com.example.rickandmorty.utils.performGetOperation
import javax.inject.Inject


class HitRepository @Inject constructor(
    private val remoteDataSource: HitRemoteDataSource,
    private val localDataSource: HitDAO
) {

    fun getHits() = performGetOperation(
        databaseQuery = { localDataSource.getAllHits() },
        networkCall = { remoteDataSource.getAllHits() },
        saveCallResult = { localDataSource.insertAll(it.hits) }
    )

    fun deleteHit(hit: Hit) {
        localDataSource.delete(hit)
    }

    fun softDelete(id: Int) {
        localDataSource.softDelete(id)
    }

}