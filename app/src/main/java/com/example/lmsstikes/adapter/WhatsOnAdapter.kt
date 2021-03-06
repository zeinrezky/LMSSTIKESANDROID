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

class WhatsOnAdapter(context : Context, list: ArrayList<Dashboard.WhatsOn>, private val listener: Listener)
    : RecyclerView.Adapter<WhatsOnAdapter.WhatsOnViewHolder>() {

    private val contexts = context
    private val itemList = list

    interface Listener{
        fun onItemClicked(data: Dashboard.WhatsOn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhatsOnViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_whats_on, parent,false)
        return WhatsOnViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: WhatsOnViewHolder, position: Int) {

        UtilityHelper.setImage(contexts, itemList[position].image, holder.image)
        holder.title.text = itemList[position].title
        holder.date.text = UtilityHelper.getSdfDayMonthYear(itemList[position].date)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(itemList[position])
        }
    }

    inner class WhatsOnViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val date: TextView = itemView.findViewById(R.id.date)
    }

}