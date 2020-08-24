package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Session (
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_course")
    val id_course: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("date_start")
    val date_start: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("date_end")
    val date_end: String,
    @SerializedName("topic")
    var topic: ArrayList<Topic>?
)