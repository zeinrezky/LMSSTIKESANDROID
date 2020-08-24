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
    @GET("schedule")
    suspend fun schedule(): NetworkResponse<ResponseSuccess<ArrayList<Schedule>>, ResponseError>

    @GET("course")
    suspend fun course(@Query("id_schedule") id: Int): NetworkResponse<ResponseSuccess<ArrayList<Course>>, ResponseError>

    @GET("session")
    suspend fun session(@Query("id_course") id: Int,
                        @Query("month") month: Int): NetworkResponse<ResponseSuccess<ArrayList<Session>>, ResponseError>

    @GET("topic")
    suspend fun topic(@Query("id_session") id: Int): NetworkResponse<ResponseSuccess<ArrayList<Topic>>, ResponseError>
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
    suspend fun schedule(): NetworkResponse<ResponseSuccess<ArrayList<Schedule>>, ResponseError> {
        return userService.schedule()
    }
    suspend fun course(id: Int): NetworkResponse<ResponseSuccess<ArrayList<Course>>, ResponseError> {
        return userService.course(id)
    }
    suspend fun session(id: Int, month: Int): NetworkResponse<ResponseSuccess<ArrayList<Session>>, ResponseError> {
        return userService.session(id, month)
    }
    suspend fun topic(id: Int): NetworkResponse<ResponseSuccess<ArrayList<Topic>>, ResponseError> {
        return userService.topic(id)
    }
}