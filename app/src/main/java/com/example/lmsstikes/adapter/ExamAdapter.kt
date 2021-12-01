package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Exam
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.util.Constant

class ExamAdapter(context : Context, list: ArrayList<Exam>)
    : RecyclerView.Adapter<ExamAdapter.ExamViewHolder>() {

    private val contexts = context
    private val itemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_exam, parent,false)
        return ExamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {

        holder.code.text = itemList[position].code
        holder.name.text = itemList[position].name
        holder.examClass.text = itemList[position].exam_class
        holder.note.text = itemList[position].note
        holder.download.text = UtilityHelper.getSdfDayMonthYearTime(itemList[position].download)
        holder.deadline.text = UtilityHelper.getSdfDayMonthYearTime(itemList[position].deadline)
        if (AppPreference.getLoginData().role == Constant.Role.DOSEN){
            holder.status.visibility = View.GONE
            holder.statusText.visibility = View.GONE
        } else {
            holder.status.text = itemList[position].status
        }

        holder.room.text = itemList[position].room
        holder.location.text = itemList[position].location

    }

    inner class ExamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val code: TextView = itemView.findViewById(R.id.code)
        val name: TextView = itemView.findViewById(R.id.name)
        val examClass: TextView = itemView.findViewById(R.id.exam_class)
        val note: TextView = itemView.findViewById(R.id.note)
        val download: TextView = itemView.findViewById(R.id.download)
        val deadline: TextView = itemView.findViewById(R.id.deadline)
        val status: TextView = itemView.findViewById(R.id.status)
        val room: TextView = itemView.findViewById(R.id.room)
        val location: TextView = itemView.findViewById(R.id.location)
        val statusText: TextView = itemView.findViewById(R.id.status_text)

    }

}