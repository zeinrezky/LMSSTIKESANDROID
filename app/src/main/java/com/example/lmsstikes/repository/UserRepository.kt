package com.example.lmsstikes.repository

import com.example.lmsstikes.model.*
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface UserService {

    @POST("auth/login")
    suspend fun login(@Body login: Login): NetworkResponse<ResponseSuccess<Profile>, ResponseError>

    @GET("consumer/logout")
    suspend fun logout(): NetworkResponse<ResponseSuccess<Nothing>, ResponseError>

    @GET("home")
    suspend fun home(@Query("knowledge") knowledge: Int,
                     @Query("whatson") whatson: Int,
                     @Query("campus_directory") campus_dir: Int,
                     @Query("campus_about") campus_about: Int): NetworkResponse<ResponseSuccess<Dashboard>, ResponseError>
}

open class UserRepository(private val userService: UserService) {

    suspend fun login(login: Login): NetworkResponse<ResponseSuccess<Profile>, ResponseError> {
        return userService.login(login)
    }

    suspend fun logout(): NetworkResponse<ResponseSuccess<Nothing>, ResponseError> {
        return userService.logout()
    }
    suspend fun home(knowledge: Int, whatson: Int, campus_dir: Int, campus_about: Int): NetworkResponse<ResponseSuccess<Dashboard>, ResponseError> {
        return userService.home(knowledge, whatson, campus_dir, campus_about)
    }
}