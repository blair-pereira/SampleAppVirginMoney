package com.example.virginmoneysampleapp.ui.people

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoneysampleapp.R
import com.example.virginmoneysampleapp.databinding.FragmentPeopleBinding
import com.example.virginmoneysampleapp.model.PeopleModelItem
import com.example.virginmoneysampleapp.ui.main.MainViewModel
import com.example.virginmoneysampleapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : Fragment(R.layout.fragment_people) {

    private lateinit var binding: FragmentPeopleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel by viewModels<MainViewModel>()

        binding = FragmentPeopleBinding.bind(view)

        val recyclerPeople = binding.peopleRecycler
        recyclerPeople.background = AppCompatResources.getDrawable(requireContext(), R.color.white)
        recyclerPeople.layoutManager = LinearLayoutManager(context)

        val srl = binding.srlID

        srl.setOnRefreshListener {
            srl.isRefreshing = true
            viewmodel.getData(true)
        }

        if (viewmodel.myIndex == 0) {
            viewmodel.getData(true)
            viewmodel.myIndex++
        }


        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.data.collect() { state ->
                when (state) {
                    is UIState.loading -> {
                        srl.isRefreshing = true
                        //Thread.sleep(20000)
                        srl.isRefreshing = false
                    }
                    is UIState.Fail -> {
                        srl.isRefreshing = false
                    }
                    is UIState.Success<*> -> {
                        recyclerPeople.adapter = PeopleAdapter(
                            (state.uiResponse as ArrayList<PeopleModelItem>),
                            requireActivity()
                        )
                        srl.isRefreshing = false
                    }

                }
            }

        }
    }
}