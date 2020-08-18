package com.example.lmsstikes.repository

import com.example.lmsstikes.model.Login
import com.example.lmsstikes.model.ResponseError
import com.example.lmsstikes.model.ResponseSuccess
import com.example.lmsstikes.model.User
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface UserService {

    @POST("consumer/login")
    suspend fun login(@Body login: Login): NetworkResponse<ResponseSuccess<User>, ResponseError>

    @GET("consumer/logout")
    suspend fun logout(): NetworkResponse<ResponseSuccess<Nothing>, ResponseError>
}

open class UserRepository(private val userService: UserService) {

    suspend fun login(login: Login): NetworkResponse<ResponseSuccess<User>, ResponseError> {
        return userService.login(login)
    }

    suspend fun logout(): NetworkResponse<ResponseSuccess<Nothing>, ResponseError> {
        return userService.logout()
    }
}