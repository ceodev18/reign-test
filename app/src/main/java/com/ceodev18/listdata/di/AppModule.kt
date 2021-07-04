package com.ceodev18.listdata.di

import com.ceodev18.listdata.data.local.HitDAO
import android.content.Context
import com.ceodev18.listdata.data.local.AppDatabase
import com.ceodev18.listdata.data.remote.HitRemoteDataSource
import com.ceodev18.listdata.data.remote.HitService
import com.ceodev18.listdata.data.repository.HitRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://hn.algolia.com/api/v1/")

            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideHitService(retrofit: Retrofit): HitService = retrofit.create(HitService::class.java)

    @Singleton
    @Provides
    fun provideHitRemoteDataSource(hitservice: HitService) = HitRemoteDataSource(hitservice)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideHitDAO(db: AppDatabase) = db.hitDAO()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: HitRemoteDataSource,
                          localDataSource: HitDAO
    ) =
            HitRepository(remoteDataSource, localDataSource)
}