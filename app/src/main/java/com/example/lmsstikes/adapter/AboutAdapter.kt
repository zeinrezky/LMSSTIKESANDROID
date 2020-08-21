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

class AboutAdapter(context : Context, list: ArrayList<Dashboard.About>)
    : RecyclerView.Adapter<AboutAdapter.AboutViewHolder>() {

    private val contexts = context
    private val itemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_about, parent,false)
        return AboutViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {

        UtilityHelper.setImage(contexts, itemList[position].image, holder.image)
        holder.title.text = itemList[position].title
    }

    inner class AboutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
    }

}