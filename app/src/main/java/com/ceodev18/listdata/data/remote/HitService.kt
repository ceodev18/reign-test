package com.ceodev18.listdata.data.remote

import com.ceodev18.listdata.data.entities.Content
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HitService {
    @GET("search_by_date?query=mobile")
    suspend fun getAllHits(): Response<Content>
}