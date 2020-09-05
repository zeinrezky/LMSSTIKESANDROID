package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Session

class ForumSessionAdapter(context : Context, list: ArrayList<Session>, private val listener: Listener)
    : RecyclerView.Adapter<ForumSessionAdapter.ForumSessionViewHolder>() {

    private val contexts = context
    private val itemList = list

    interface Listener{
        fun onItemClicked(data: Session)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumSessionViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_forum_session, parent,false)
        return ForumSessionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ForumSessionViewHolder, position: Int) {

        holder.startDate.text = UtilityHelper.getSdfDMY(itemList[position].date_start)
        holder.endDate.text = UtilityHelper.getSdfDMY(itemList[position].date_end)
        holder.name.text = itemList[position].name

        holder.itemView.setOnClickListener {
            listener.onItemClicked(itemList[position])
        }
    }

    inner class ForumSessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val startDate: TextView = itemView.findViewById(R.id.start_date)
        val endDate: TextView = itemView.findViewById(R.id.end_date)
        val name: TextView = itemView.findViewById(R.id.name)

    }

}