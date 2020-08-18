package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class ResponseSuccess<T> (
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T
)