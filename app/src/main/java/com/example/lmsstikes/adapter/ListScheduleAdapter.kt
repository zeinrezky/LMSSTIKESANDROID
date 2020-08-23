package com.example.lmsstikes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.lmsstikes.R
import com.example.lmsstikes.model.Schedule
import kotlinx.android.synthetic.main.item_schedule.view.*

class ListScheduleAdapter (val list: ArrayList<Schedule>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_schedule, parent, false)

        view.name.text = list[position].name

        return view
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}