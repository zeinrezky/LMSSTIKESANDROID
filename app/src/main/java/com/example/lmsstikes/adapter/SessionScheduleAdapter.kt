package com.example.lmsstikes.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Session

class SessionScheduleAdapter(context : Context, list: ArrayList<Session>)
    : RecyclerView.Adapter<SessionScheduleAdapter.SessionScheduleViewHolder>() {

    private val contexts = context
    private val itemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionScheduleViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_session_schedule, parent,false)
        return SessionScheduleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SessionScheduleViewHolder, position: Int) {

        holder.startDate.text = itemList[position].time_start
        holder.endDate.text = itemList[position].time_end
        holder.type.text = itemList[position].type
        holder.name.text = itemList[position].name

        when (itemList[position].type) {
            "VC" -> holder.indicator.apply {
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorPrimary)
            }
            "OL" -> holder.indicator.apply {
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorBlue)
            }
            "EXAM" -> holder.indicator.apply {
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorOrange)
            }
        }

    }

    inner class SessionScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val indicator: ImageView = itemView.findViewById(R.id.indicator)
        val type: TextView = itemView.findViewById(R.id.type)
        val startDate: TextView = itemView.findViewById(R.id.start_date)
        val endDate: TextView = itemView.findViewById(R.id.end_date)
        val name: TextView = itemView.findViewById(R.id.name)


    }

}