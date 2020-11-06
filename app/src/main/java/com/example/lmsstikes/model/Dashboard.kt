package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Dashboard (
    @SerializedName("profile")
    var profile: Profile,
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
        val id: Int,
        @SerializedName("img")
        val image: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("link")
        val link: String
    )
    data class Announcement (
        @SerializedName("id_announcement")
        val id: Int,
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
        val id: Int,
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
        val id: Int,
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
        val id: Int,
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
    data class WhatsOnList (
        @SerializedName("data")
        var data: ArrayList<WhatsOn>,
        @SerializedName("page")
        var page: Int,
        @SerializedName("perPage")
        var perPage: Int,
        @SerializedName("sortDir")
        var sortDir: String,
        @SerializedName("sortBy")
        var sortBy: String,
        @SerializedName("search")
        var search: String,
        @SerializedName("total")
        var total: Int,
        @SerializedName("totalPage")
        var totalPage: Int
    )
    data class KnowledgeList (
        @SerializedName("data")
        var data: ArrayList<Knowledge>,
        @SerializedName("page")
        var page: Int,
        @SerializedName("perPage")
        var perPage: Int,
        @SerializedName("sortDir")
        var sortDir: String,
        @SerializedName("sortBy")
        var sortBy: String,
        @SerializedName("search")
        var search: String,
        @SerializedName("total")
        var total: Int,
        @SerializedName("totalPage")
        var totalPage: Int
    )
}