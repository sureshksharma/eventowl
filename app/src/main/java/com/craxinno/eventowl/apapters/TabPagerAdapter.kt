package com.craxinno.eventowl.apapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.craxinno.eventowl.ui.home.AgendaListFragment

class TabPagerAdapter(activity: AppCompatActivity, private val listOfTitle: List<String>) : FragmentStateAdapter(activity) {

    override fun getItemCount() = listOfTitle.size

    

    override fun createFragment(position: Int): Fragment {
        return AgendaListFragment()
    }
}