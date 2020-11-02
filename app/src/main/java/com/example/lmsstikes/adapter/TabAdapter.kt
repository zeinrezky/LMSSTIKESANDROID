package com.example.lmsstikes.adapter

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lmsstikes.view.menu.ExamFragment
import com.example.lmsstikes.view.menu.ScheduleFragment

internal class TabAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> ScheduleFragment.newInstance(false)
            else -> ExamFragment.newInstance(false)
        }
    }

    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    companion object {
        private const val TAB_COUNT = 2
    }
}