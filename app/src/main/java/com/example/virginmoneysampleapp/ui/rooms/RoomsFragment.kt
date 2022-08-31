package com.example.virginmoneysampleapp.ui.rooms

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoneysampleapp.R
import com.example.virginmoneysampleapp.databinding.FragmentRoomsBinding
import com.example.virginmoneysampleapp.model.RoomModelItem
import com.example.virginmoneysampleapp.ui.main.MainViewModel
import com.example.virginmoneysampleapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomsFragment:Fragment(R.layout.fragment_rooms) {
    private lateinit var binding: FragmentRoomsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel by viewModels<MainViewModel>()
        binding = FragmentRoomsBinding.bind(view)

        val recyclerRooms = binding.roomsRecycler
        recyclerRooms.background = AppCompatResources.getDrawable(requireContext(), R.color.white)
        recyclerRooms.layoutManager = LinearLayoutManager(context)

        val swiperl = binding.srlID

        swiperl.setOnRefreshListener {
            swiperl.isRefreshing = true
            viewmodel.getData(false)
        }

        viewmodel.getData(false)


        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.data.collect() { state ->
                when (state) {
                    is UIState.loading -> {
                        Log.d("API Response: ", "LOADING")
                        swiperl.isRefreshing = true
                    }
                    is UIState.Fail -> {
                        Log.d("API Response: ", "Error -> ${state.error}")
                        swiperl.isRefreshing = false
                    }
                    is UIState.Success<*> -> {
                        recyclerRooms.adapter = RoomsAdapter((state.uiResponse as ArrayList<RoomModelItem>), requireActivity())
                        swiperl.isRefreshing = false
                    }
                }
            }
        }
    }
}