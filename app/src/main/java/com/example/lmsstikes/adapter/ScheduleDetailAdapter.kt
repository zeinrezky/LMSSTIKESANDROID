package com.example.lmsstikes.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Session
import com.example.lmsstikes.util.Constant
import com.example.lmsstikes.view.main.WebViewActivity

class ScheduleDetailAdapter(context : Context, list: ArrayList<Session>)
    : RecyclerView.Adapter<ScheduleDetailAdapter.ScheduleDetailViewHolder>() {

    private val contexts = context
    private val itemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDetailViewHolder {
        val view = LayoutInflater.from(contexts).inflate(R.layout.item_schedule_detail, parent,false)
        return ScheduleDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ScheduleDetailViewHolder, position: Int) {

        holder.view.visibility = View.GONE
        holder.code.text = itemList[position].course_code
        holder.name.text = itemList[position].name
        holder.type.text = itemList[position].type
        holder.sClass.text = itemList[position].session_class
        holder.startTime.text = itemList[position].time_start
        holder.endTime.text = itemList[position].time_end
        holder.date.text = UtilityHelper.getSdfDMY(itemList[position].date_start)
        holder.itemView.setOnClickListener {
            if (holder.view.visibility == View.GONE) {
                holder.view.visibility = View.VISIBLE
//                holder.imgExpand.rotation = 90F
            } else {
                holder.view.visibility = View.GONE
//                holder.imgExpand.rotation = 270F
            }
        }
        when (itemList[position].type) {
            "VC" -> holder.indicator.apply {
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorPrimary)
            }
            "ONSITE" -> holder.indicator.apply {
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorBlue)
            }
            "EXAM" -> holder.indicator.apply {
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorOrange)
            }
            "EVENTS" -> holder.indicator.apply {
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorYellow)
            }
        }
        holder.link.setOnClickListener {
//            val browserIntent =
//                Intent(Intent.ACTION_VIEW, Uri.parse(itemList[position].link))
//            contexts.startActivity(browserIntent)
            val intent = Intent(contexts, WebViewActivity::class.java)
            intent.putExtra(Constant.Header.URL, itemList[position].link)
            contexts.startActivity(intent)
        }
    }

    inner class ScheduleDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val indicator: ImageView = itemView.findViewById(R.id.indicator)
        val link: ImageView = itemView.findViewById(R.id.ic_icon)
        val name: TextView = itemView.findViewById(R.id.name)
        val code: TextView = itemView.findViewById(R.id.code)
        val type: TextView = itemView.findViewById(R.id.s_type)
        val sClass: TextView = itemView.findViewById(R.id.s_class)
        val date: TextView = itemView.findViewById(R.id.date)
        val startTime: TextView = itemView.findViewById(R.id.start_time)
        val endTime: TextView = itemView.findViewById(R.id.end_time)
        val view: ConstraintLayout = itemView.findViewById(R.id.view)

    }

}