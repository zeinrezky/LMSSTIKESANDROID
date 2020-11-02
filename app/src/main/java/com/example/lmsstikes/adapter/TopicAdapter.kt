package com.example.lmsstikes.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.model.Topic
import com.example.lmsstikes.util.Constant
import com.example.lmsstikes.view.main.WebViewActivity


class TopicAdapter(context : Context, list: ArrayList<Topic>)
    : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    private val contexts = context
    private val itemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_topic, parent,false)
        return TopicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {

        holder.subtitle.text = itemList[position].subtitle
        holder.desc.text = itemList[position].desc

        if (itemList[position].attachment != null){
            holder.attachment.visibility = View.VISIBLE
            holder.attachment.setOnClickListener {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(itemList[position].attachment))
                contexts.startActivity(browserIntent)
            }
        }

        if (itemList[position].link != null){
            holder.link.visibility = View.VISIBLE
            holder.link.setOnClickListener {
                val intent = Intent(contexts, WebViewActivity::class.java)
                intent.putExtra(Constant.Header.URL, itemList[position].link)
                contexts.startActivity(intent)
            }
        }
    }

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        val desc: TextView = itemView.findViewById(R.id.desc)
        val attachment: ImageView = itemView.findViewById(R.id.attachment)
        val link: ImageView = itemView.findViewById(R.id.link)


    }

}