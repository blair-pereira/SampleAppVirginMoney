package com.example.virginmoneysampleapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.virginmoneysampleapp.R
import com.example.virginmoneysampleapp.databinding.FragmentMainBinding
import com.example.virginmoneysampleapp.util.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        val tabLayout = binding.tableLayout
        val viewPager = binding.pager

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when{
                position == 0 -> {tab.text = "People"
                    //tab.setIcon(R.drawable.ic_launcher_background)
                }
                position == 1 -> tab.text = "Rooms"
            }
        }.attach()
    }
}