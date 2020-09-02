package com.example.lmsstikes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.model.Score

class ScoreAdapter(context : Context, list: ArrayList<Score>)
    : RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    private val contexts = context
    private val itemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_score, parent,false)
        return ScoreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {

        holder.view.visibility = View.GONE
        holder.code.text = itemList[position].course_code
        holder.name.text = itemList[position].course_name
        holder.finalExam.text = itemList[position].final_exam.toString()
        holder.courseClass.text = itemList[position].course_class
        holder.finalScore.text = itemList[position].final_score.toString()
        holder.assignment.text = itemList[position].assignment.toString()
        holder.forumDiscussion.text = itemList[position].forum_discussion.toString()
        holder.vcAttendance.text = itemList[position].vc_attendance.toString()
        holder.quiz.text = itemList[position].quiz.toString()
        holder.grade.text = itemList[position].grade

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

    inner class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val code: TextView = itemView.findViewById(R.id.code)
        val name: TextView = itemView.findViewById(R.id.course_name)
        val finalExam: TextView = itemView.findViewById(R.id.final_exam)
        val courseClass: TextView = itemView.findViewById(R.id.course_class)
        val assignment: TextView = itemView.findViewById(R.id.assignment)
        val forumDiscussion: TextView = itemView.findViewById(R.id.forum_discussion)
        val vcAttendance: TextView = itemView.findViewById(R.id.vc_attendance)
        val quiz: TextView = itemView.findViewById(R.id.quiz)
        val finalScore: TextView = itemView.findViewById(R.id.final_score)
        val grade: TextView = itemView.findViewById(R.id.grade)
        val view: ConstraintLayout = itemView.findViewById(R.id.view)

    }

}