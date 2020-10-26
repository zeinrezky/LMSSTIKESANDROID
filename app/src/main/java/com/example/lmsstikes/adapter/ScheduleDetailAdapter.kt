package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.model.Session

class ScheduleDetailAdapter(context : Context, list: ArrayList<Session>)
    : RecyclerView.Adapter<ScheduleDetailAdapter.ScheduleDetailViewHolder>() {

    private val contexts = context
    private val itemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDetailViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_schedule_detail, parent,false)
        return ScheduleDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ScheduleDetailViewHolder, position: Int) {

        holder.view.visibility = View.GONE
        holder.code.text = itemList[position].type
        holder.name.text = itemList[position].name
        holder.type.text = itemList[position].type
        holder.sClass.text = itemList[position].type
        holder.startTime.text = itemList[position].time_start
        holder.endTime.text = itemList[position].time_end
        holder.itemView.setOnClickListener {
            if (holder.view.visibility == View.GONE) {
                holder.view.visibility = View.VISIBLE
//                holder.imgExpand.rotation = 90F
            } else {
                holder.view.visibility = View.GONE
//                holder.imgExpand.rotation = 270F
            }
        }
    }

    inner class ScheduleDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name)
        val code: TextView = itemView.findViewById(R.id.code)
        val type: TextView = itemView.findViewById(R.id.s_type)
        val sClass: TextView = itemView.findViewById(R.id.s_class)
        val date: TextView = itemView.findViewById(R.id.date)
        val startTime: TextView = itemView.findViewById(R.id.start_time)
        val endTime: TextView = itemView.findViewById(R.id.end_time)
        val view: ConstraintLayout = itemView.findViewById(R.id.view)

    }

}