package com.example.virginmoneysampleapp.util

sealed class UIState {
    object loading : UIState()
    class Success<T>(val uiResponse: T) : UIState()
    class Fail(val error: String) : UIState()
}