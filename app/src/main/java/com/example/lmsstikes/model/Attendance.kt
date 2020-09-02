package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

class Attendance (
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_schedule")
    val id_schedule: String,
    @SerializedName("course_name")
    val course_name: String,
    @SerializedName("course_desc")
    val course_desc: String,
    @SerializedName("course_code")
    val course_code: String,
    @SerializedName("course_type")
    val course_type: String,
    @SerializedName("course_class")
    val course_class: String,
    @SerializedName("max_absence")
    val max_absence: Int,
    @SerializedName("total_session")
    val total_session: Int,
    @SerializedName("session_done")
    val session_done: Int,
    @SerializedName("total_absence")
    val total_absence: Int,
    @SerializedName("attendance")
    val attendance: Int
)