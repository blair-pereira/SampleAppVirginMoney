package com.example.virginmoneysampleapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.virginmoneysampleapp.repo.Repository
import com.example.virginmoneysampleapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _data: MutableStateFlow<UIState> = MutableStateFlow(UIState.loading)
    val data: StateFlow<UIState> get() = _data
    var myIndex: Int = 0

    fun getData(people: Boolean) {
        _data.value = (UIState.loading)
        CoroutineScope(Dispatchers.IO).launch {
            if (people) {
                var response = repository.getPeople()
                if (response.isSuccessful) {
                    _data.value = (
                            response.body()?.let { success ->
                                UIState.Success(success)
                            }!!
                            )
                } else {
                    _data.value = (
                            UIState.Fail("")
                            )
                }
            } else {
                var response = repository.getRooms()
                if (response.isSuccessful) {
                    _data.value = (
                            response.body()?.let { success ->
                                UIState.Success(success)
                            }!!
                            )
                } else {
                    _data.value = (
                            UIState.Fail("")
                            )
                }
            }
        }
    }
}