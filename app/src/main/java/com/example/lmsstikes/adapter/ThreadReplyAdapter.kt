package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.model.Thread

class ThreadReplyAdapter(context : Context, list: ArrayList<Thread.ThreadList>)
    : RecyclerView.Adapter<ThreadReplyAdapter.ThreadReplyViewHolder>() {

    private val contexts = context
    private val itemList = list


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreadReplyViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_thread_reply, parent,false)
        return ThreadReplyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ThreadReplyViewHolder, position: Int) {

        holder.content.text = itemList[position].content
        holder.datePost.text = itemList[position].date_post
        holder.userName.text = itemList[position].id_poster.toString()

    }

    inner class ThreadReplyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val userType: TextView = itemView.findViewById(R.id.user_type)
        val datePost: TextView = itemView.findViewById(R.id.date_post)
        val content: TextView = itemView.findViewById(R.id.content)


    }

}