package com.example.lmsstikes.repository

import com.example.lmsstikes.model.*
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface UserService {

    @POST("consumer/login")
    suspend fun login(@Body login: Login): NetworkResponse<ResponseSuccess<User>, ResponseError>

    @GET("consumer/logout")
    suspend fun logout(): NetworkResponse<ResponseSuccess<Nothing>, ResponseError>

    @GET("home")
    suspend fun home(): NetworkResponse<ResponseSuccess<Dashboard>, ResponseError>
}

open class UserRepository(private val userService: UserService) {

    suspend fun login(login: Login): NetworkResponse<ResponseSuccess<User>, ResponseError> {
        return userService.login(login)
    }

    suspend fun logout(): NetworkResponse<ResponseSuccess<Nothing>, ResponseError> {
        return userService.logout()
    }
    suspend fun home(): NetworkResponse<ResponseSuccess<Dashboard>, ResponseError> {
        return userService.home()
    }
}