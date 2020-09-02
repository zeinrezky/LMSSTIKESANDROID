package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Score (
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
@SerializedName("final_exam")
val final_exam: Int,
@SerializedName("assignment")
val assignment: Int,
@SerializedName("forum_discussion")
val forum_discussion: Int,
@SerializedName("vc_attendance")
val vc_attendance: Int,
@SerializedName("quiz")
val quiz: Int,
@SerializedName("final_score")
val final_score: Int,
@SerializedName("grade")
val grade: String
)