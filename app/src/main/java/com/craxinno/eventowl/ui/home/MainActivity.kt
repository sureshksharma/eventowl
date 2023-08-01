package com.craxinno.eventowl.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.craxinno.eventowl.apapters.TabPagerAdapter
import com.craxinno.eventowl.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabLayoutWithViewPager()

    }

    private fun setupTabLayoutWithViewPager() {
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        val tabList = getDateList()

        val adapter = TabPagerAdapter(this, tabList)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()

    }

    private fun getDateList() : List<String> {
        val dateList = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())
        dateList.add(format.format(calendar.time))
        for (i in 1..2) {
            calendar.add(Calendar.DATE, i)
            dateList.add(format.format(calendar.time))
        }
        Log.e("TAG", "getDateList: $dateList")
        return dateList
    }

}