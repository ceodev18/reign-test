package com.ceodev18.listdata.data.repository

import com.ceodev18.listdata.data.local.HitDAO
import com.ceodev18.listdata.data.remote.HitRemoteDataSource
import com.example.rickandmorty.utils.performGetOperation
import javax.inject.Inject



class HitRepository @Inject constructor(
    private val remoteDataSource: HitRemoteDataSource,
    private val localDataSource: HitDAO
) {

    /*fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.get(id) },
        saveCallResult = { localDataSource.insert(it) }
    )*/

    fun getHits() = performGetOperation(
        databaseQuery = { localDataSource.getAllHits() },
        networkCall = { remoteDataSource.getAllHits() },
        saveCallResult = { localDataSource.insertAll(it.hits) }
    )
}