package com.example.virginmoneysampleapp.model

data class RoomModelItem(
    val createdAt: String,
    val id: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int,
    val name: String
)