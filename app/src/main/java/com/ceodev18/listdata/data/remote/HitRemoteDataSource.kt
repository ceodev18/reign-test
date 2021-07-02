package com.ceodev18.listdata.data.remote

import javax.inject.Inject

class HitRemoteDataSource @Inject constructor(
    private val hitService: HitService
): BaseDataSource() {

    suspend fun getAllHits() = getResult { hitService.getAllHits()}
    //suspend fun getCharacter(id: Int) = getResult { characterService.getCharacter(id) }
}