package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Session
import com.example.lmsstikes.model.Topic

class SessionAdapter(context : Context, list: ArrayList<Session>)
    : RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {

    private val contexts = context
    private val itemList = list

//    interface Listener{
//        fun onItemClicked(data: Int)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_session, parent,false)
        return SessionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {

        holder.id.text = itemList[position].id.toString()
        holder.startDate.text = UtilityHelper.getSdfDMY(itemList[position].date_start)
        holder.endDate.text = UtilityHelper.getSdfDMY(itemList[position].date_end)
        holder.type.text = itemList[position].type
        holder.name.text = itemList[position].name

        holder.rvTopic.layoutManager = LinearLayoutManager(contexts)
        holder.rvTopic.adapter = contexts.let {
            itemList[position].topic?.let { it1 -> TopicAdapter(it, it1) }
        }
        holder.itemView.setOnClickListener {
            if (holder.rvTopic.visibility == View.GONE) {
                holder.rvTopic.visibility = View.VISIBLE
                holder.dropDown.rotation = 180F
            } else {
                holder.rvTopic.visibility = View.GONE
                holder.dropDown.rotation = 0F
            }
        }
    }

    inner class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id: TextView = itemView.findViewById(R.id.id)
        val type: TextView = itemView.findViewById(R.id.type)
        val startDate: TextView = itemView.findViewById(R.id.start_date)
        val endDate: TextView = itemView.findViewById(R.id.end_date)
        val name: TextView = itemView.findViewById(R.id.name)
        val rvTopic: RecyclerView = itemView.findViewById(R.id.rv_topic)
        val dropDown: ImageView = itemView.findViewById(R.id.dropdown)


    }

}