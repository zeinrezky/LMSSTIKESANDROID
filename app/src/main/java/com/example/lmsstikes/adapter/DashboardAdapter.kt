package com.example.lmsstikes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lmsstikes.R
import com.example.lmsstikes.model.User
import kotlinx.android.synthetic.main.item_announcement.view.*


class DashboardAdapter(val list: ArrayList<User>, val type: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfMovies = listOf<User>()
    interface DashboardListener{
        fun onItemClicked(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CellType.ANNOUNCEMENT.ordinal -> AnnouncementViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_announcement, parent, false))
            CellType.KNOWLEDGE.ordinal -> KnowledgeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_knowledge, parent, false))
            CellType.WHATSON.ordinal -> WhatsOnViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_whats_on, parent, false))
            CellType.CAMPUS_DIR.ordinal -> WhatsOnViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_campus_dir, parent, false))
            else -> KnowledgeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_knowledge, parent, false))
        }
    }


    override fun getItemCount(): Int = list.size
    //add 2 size in itemcount because we are going to add header and footer cell in list

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            CellType.ANNOUNCEMENT.ordinal -> {
                val viewHolder = holder as AnnouncementViewHolder
                viewHolder.bindView(listOfMovies[position - 1])
            }
            CellType.KNOWLEDGE.ordinal -> {
                val viewHolder = holder as KnowledgeViewHolder
                viewHolder.bindView(listOfMovies[position - 1])
            }
            CellType.WHATSON.ordinal -> {
                val viewHolder = holder as WhatsOnViewHolder
                viewHolder.bindView(listOfMovies[position - 1])
            }
            CellType.CAMPUS_DIR.ordinal -> {
                val viewHolder = holder as WhatsOnViewHolder
                viewHolder.bindView(listOfMovies[position - 1])
            }
        }
    }


    /***
     * This method will return cell type base on position
     */
    override fun getItemViewType(position: Int): Int {
        return type
    }

    /***
     * Enum class for recyclerview Cell type
     */
    enum class CellType(viewType: Int) {
        ANNOUNCEMENT(0),
        KNOWLEDGE(1),
        WHATSON(2),
        CAMPUS_DIR(3)
    }

    fun setMovieList(listOfMovies: List<User>) {
        this.listOfMovies = listOfMovies
        notifyDataSetChanged()
    }

    class AnnouncementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(list: User) {
            itemView.title.text = ""
            itemView.date.text = ""
        }
    }
    class KnowledgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(list: User) {
            itemView.title.text = ""
            itemView.date.text = ""
        }
    }
    class WhatsOnViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(list: User) {
            itemView.title.text = ""
            itemView.date.text = ""
        }
    }
    class CampusDirViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(list: User) {
            itemView.title.text = ""
            itemView.date.text = ""
        }
    }
}

