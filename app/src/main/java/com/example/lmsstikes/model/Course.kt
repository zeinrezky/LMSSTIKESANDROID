package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Course (
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
    @SerializedName("type")
    val type: String,
    @SerializedName("class")
    val course_class: String
)