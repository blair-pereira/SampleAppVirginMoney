package com.example.virginmoneysampleapp.repo

import com.example.virginmoneysampleapp.model.PeopleModelItem
import com.example.virginmoneysampleapp.model.RoomModelItem
import retrofit2.Response

interface Repository {

    suspend fun getPeople():Response<ArrayList<PeopleModelItem>>
    suspend fun getRooms():Response<ArrayList<RoomModelItem>>
}