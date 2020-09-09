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
import com.example.lmsstikes.model.Course
import com.example.lmsstikes.model.Thread

class ThreadReplyAdapter(context : Context, list: ArrayList<Thread.ThreadList>, private val listener: Listener)
    : RecyclerView.Adapter<ThreadReplyAdapter.ThreadReplyViewHolder>() {

    private val contexts = context
    private val itemList = list

    interface Listener{
        fun onBtnRemoveClick(id: Int)
        fun onQuoteClick(content: Thread.ThreadList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreadReplyViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_thread_reply, parent,false)
        return ThreadReplyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ThreadReplyViewHolder, position: Int) {

        holder.content.text = itemList[position].content
        holder.userType.text = itemList[position].role
        holder.datePost.text = UtilityHelper.getSdfDayMonthYearTime(itemList[position].date_post)
        holder.userName.text = itemList[position].poster_name
        UtilityHelper.setImage(contexts, itemList[position].img, holder.avatar)
        holder.btnRemove.setOnClickListener {
            listener.onBtnRemoveClick(itemList[position].id_thread)
        }
        holder.quote.setOnClickListener {
            listener.onQuoteClick(itemList[position])
        }
        if (itemList[position].quote.isNotEmpty()){
            holder.quoteMsg.visibility = View.VISIBLE
            holder.quoteMsg.text = itemList[position].quote
        }

    }

    inner class ThreadReplyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val userType: TextView = itemView.findViewById(R.id.user_type)
        val datePost: TextView = itemView.findViewById(R.id.date_post)
        val content: TextView = itemView.findViewById(R.id.content)
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
        val btnRemove: ImageView = itemView.findViewById(R.id.btn_remove)
        val quote: TextView = itemView.findViewById(R.id.quote)
        val quoteMsg: TextView = itemView.findViewById(R.id.quote_msg)

    }

}