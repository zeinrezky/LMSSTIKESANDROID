package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.model.Thread

class ThreadAdapter(context : Context, list: ArrayList<Thread.ThreadList>, private val listener: Listener)
    : RecyclerView.Adapter<ThreadAdapter.ThreadViewHolder>() {

    private val contexts = context
    private val itemList = list

    interface Listener{
        fun onItemClicked(data: Thread.ThreadList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreadViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_thread_list, parent,false)
        return ThreadViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ThreadViewHolder, position: Int) {

        holder.title.text = itemList[position].title
        holder.date.text = itemList[position].date_post
        holder.by.text = itemList[position].id_poster.toString()

        holder.itemView.setOnClickListener {
            listener.onItemClicked(itemList[position])
        }
    }

    inner class ThreadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val date: TextView = itemView.findViewById(R.id.date)
        val by: TextView = itemView.findViewById(R.id.by)

    }

}