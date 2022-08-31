package com.example.virginmoneysampleapp.api

import com.example.virginmoneysampleapp.model.PeopleModelItem
import com.example.virginmoneysampleapp.model.RoomModelItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiDetails {

    @GET("people")
    suspend fun getPeople():Response<ArrayList<PeopleModelItem>>

    @GET("rooms")
    suspend fun getRooms():Response<ArrayList<RoomModelItem>>
}