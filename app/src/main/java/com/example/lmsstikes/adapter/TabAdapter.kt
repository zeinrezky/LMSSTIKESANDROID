package com.example.lmsstikes.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.lmsstikes.view.dashboard.DashboardFragment
import com.example.lmsstikes.view.schedule.ScheduleFragment

class TabAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        ScheduleFragment.newInstance(),
        DashboardFragment.newInstance()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Schedule"
            else -> "Exam"
        }
    }
}