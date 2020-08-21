package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class ResponseSuccess<T> (
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("msg")
    val message: String,
    @SerializedName("payload")
    val data: T
)