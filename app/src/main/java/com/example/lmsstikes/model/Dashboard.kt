package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Dashboard (
    @SerializedName("knowledge")
    var knowledge: ArrayList<Knowledge>,
    @SerializedName("announcement")
    var announcement: ArrayList<Announcement>,
    @SerializedName("whatson")
    var whats_on: ArrayList<WhatsOn>,
    @SerializedName("campus_directory")
    var campus_dir: ArrayList<CampusDir>,
    @SerializedName("campus_about")
    var about: ArrayList<About>
){
    data class Knowledge (
        @SerializedName("id_knowledge")
        val id: String,
        @SerializedName("img")
        val image: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("date")
        val date: String
    )
    data class Announcement (
        @SerializedName("id_announcement")
        val id: String,
        @SerializedName("img")
        val image: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("date")
        val date: String
    )
    data class WhatsOn (
        @SerializedName("id_whatson")
        val id: String,
        @SerializedName("img")
        val image: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("date")
        val date: String
    )
    data class CampusDir (
        @SerializedName("id_campus")
        val id: String,
        @SerializedName("img")
        val image: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("date")
        val date: String
    )
    data class About (
        @SerializedName("id_campus")
        val id: String,
        @SerializedName("img")
        val image: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("date")
        val date: String
    )
}