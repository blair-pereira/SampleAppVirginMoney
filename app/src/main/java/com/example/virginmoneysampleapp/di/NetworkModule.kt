package com.example.virginmoneysampleapp.di

import com.example.virginmoneysampleapp.api.ApiDetails
import com.example.virginmoneysampleapp.api.ApiReference
import com.example.virginmoneysampleapp.repo.Repository
import com.example.virginmoneysampleapp.repo.RepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGson():Gson{
        return Gson()
    }

    @Provides
    fun retrofitBuilder(gson: Gson,okHttpClient: OkHttpClient):Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiReference.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun getApiDetail(retrofit: Retrofit):ApiDetails{
        return retrofit.create(ApiDetails::class.java)
    }

    fun getRepository(apiDetails: ApiDetails):Repository{
        return RepositoryImpl(apiDetails)
    }
}