package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.model.Course

class CourseAdapter(context : Context, list: ArrayList<Course>, private val listener: CourseAdapter.Listener)
    : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private val contexts = context
    private val itemList = list

    interface Listener{
        fun onItemClicked(data: Course)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_course, parent,false)
        return CourseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {

        holder.code.text = itemList[position].code
        holder.courseClass.text = itemList[position].course_class
        holder.type.text = itemList[position].type
        holder.name.text = itemList[position].name

        holder.itemView.setOnClickListener {
            listener.onItemClicked(itemList[position])
        }
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val code: TextView = itemView.findViewById(R.id.code)
        val type: TextView = itemView.findViewById(R.id.type)
        val courseClass: TextView = itemView.findViewById(R.id.course_class)
        val name: TextView = itemView.findViewById(R.id.name)

    }

}