package com.example.virginmoneysampleapp.util

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.virginmoneysampleapp.ui.people.PeopleFragment
import com.example.virginmoneysampleapp.ui.DetailFragment

private const val NUM_TABS = 2

open class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    :FragmentStateAdapter(fragmentManager,lifecycle) {



    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int) = when (position) {
        0 -> PeopleFragment()
        else -> DetailFragment()
    }
}