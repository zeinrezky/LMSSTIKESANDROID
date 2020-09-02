package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.model.Attendance

class AttendanceAdapter(context : Context, list: ArrayList<Attendance>)
    : RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {

    private val contexts = context
    private val itemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_attendance, parent,false)
        return AttendanceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {

        holder.view.visibility = View.GONE
        holder.code.text = itemList[position].course_code
        holder.name.text = itemList[position].course_name
        holder.type.text = itemList[position].course_type
        holder.courseClass.text = itemList[position].course_class
        holder.totalSession.text = itemList[position].total_session.toString()
        holder.maxAbsence.text = itemList[position].max_absence.toString()
        holder.totalAbsence.text = itemList[position].total_absence.toString()
        holder.sessionDone.text = itemList[position].session_done.toString()

        holder.itemView.setOnClickListener {
            if (holder.view.visibility == View.GONE) {
                holder.view.visibility = View.VISIBLE
//                holder.imgExpand.rotation = 90F
            } else {
                holder.view.visibility = View.GONE
//                holder.imgExpand.rotation = 270F
            }
        }
    }

    inner class AttendanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val code: TextView = itemView.findViewById(R.id.code)
        val name: TextView = itemView.findViewById(R.id.course_name)
        val type: TextView = itemView.findViewById(R.id.course_type)
        val courseClass: TextView = itemView.findViewById(R.id.course_class)
        val totalSession: TextView = itemView.findViewById(R.id.total_session)
        val maxAbsence: TextView = itemView.findViewById(R.id.max_absence)
        val totalAbsence: TextView = itemView.findViewById(R.id.total_absence)
        val sessionDone: TextView = itemView.findViewById(R.id.session_done)
        val view: ConstraintLayout = itemView.findViewById(R.id.view)

    }

}