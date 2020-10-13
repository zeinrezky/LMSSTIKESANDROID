package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Dashboard

class AnnouncementAdapter(context : Context, list: ArrayList<Dashboard.Announcement>, private val listener: Listener)
    : RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>() {

    private val contexts = context
    private val itemList = list

    interface Listener{
        fun onItemClicked(data: Dashboard.Announcement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_announcement, parent,false)
        return AnnouncementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        holder.title.text = itemList[position].title
        holder.date.text = UtilityHelper.getSdfDayMonthYear(itemList[position].date)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(itemList[position])
        }
    }

    inner class AnnouncementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val date: TextView = itemView.findViewById(R.id.date)
    }

}