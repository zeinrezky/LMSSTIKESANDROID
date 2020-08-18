package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

class Login {
    @SerializedName("social_id")
    var social_id : String? = null
    @SerializedName("login_type")
    var login_type : String? = null
    @SerializedName("device_type")
    var device_type : String? = "A"
    @SerializedName("device_token")
    var device_token: String? = null
    @SerializedName("uuid")
    var uuid : String? = null
    @SerializedName("os_version")
    var os_version : String? = null
    @SerializedName("app_version")
    var app_version : Int = 0
    @SerializedName("device_name")
    var device_name : String? = null
    @SerializedName("model_name")
    var model_name : String? = null
    @SerializedName("email")
    var email : String? = null
    @SerializedName("code")
    var code : String? = null
    @SerializedName("cell_phone")
    var cell_phone : String? = null
    @SerializedName("password")
    var password : String? = null
    @SerializedName("ip")
    var ip : String? = null
}