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

class CampusDirAdapter(context : Context, list: ArrayList<Dashboard.CampusDir>)
    : RecyclerView.Adapter<CampusDirAdapter.CampusDirViewHolder>() {

    private val contexts = context
    private val itemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampusDirViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_campus_dir, parent, false)
        return CampusDirViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CampusDirViewHolder, position: Int) {

        UtilityHelper.setImage(contexts, itemList[position].image, holder.image)
        holder.title.text = itemList[position].title
    }

    inner class CampusDirViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
    }
}