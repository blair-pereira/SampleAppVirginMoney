package com.example.virginmoneysampleapp.repo

import com.example.virginmoneysampleapp.api.ApiDetails
import com.example.virginmoneysampleapp.model.PeopleModelItem
import com.example.virginmoneysampleapp.model.RoomModelItem
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val apiDetails: ApiDetails):Repository {

    override suspend fun getPeople(): Response<ArrayList<PeopleModelItem>> {
        return apiDetails.getPeople()
    }

    override suspend fun getRooms(): Response<ArrayList<RoomModelItem>> {
        return apiDetails.getRooms()
    }
}