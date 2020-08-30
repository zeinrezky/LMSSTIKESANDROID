package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Thread (
    @SerializedName("data")
    var data: ArrayList<ThreadList>,
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
){
    data class ThreadList (
        @SerializedName("id_thread")
        val id_thread: Int,
        @SerializedName("id_poster")
        var id_poster: Int,
        @SerializedName("id_session")
        var id_session: Int,
        @SerializedName("id_parent")
        var id_parent: Int,
        @SerializedName("id_reply_to")
        var id_reply_tp: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("date_post")
        val date_post: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("type")
        val type: String
    )
}