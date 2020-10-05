package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Exam (
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_schedule")
    val id_schedule: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("class")
    val exam_class: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("room")
    val room: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("download")
    val download: String,
    @SerializedName("deadline")
    val deadline: String,
    @SerializedName("status")
    val status: String

)