package com.example.lmsstikes.repository

import com.example.lmsstikes.model.*
import com.haroldadmin.cnradapter.NetworkResponse
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
    @GET("thread/list")
    suspend fun thread(@Query("id_session") id: Int): NetworkResponse<ResponseSuccess<Thread>, ResponseError>
    @POST("thread/create")
    suspend fun threadCreate(@Body thread: Thread.Create): NetworkResponse<ResponseSuccess<Thread.ThreadList>, ResponseError>
    @POST("thread/delete")
    suspend fun threadDelete(@Body thread: Thread.Delete): NetworkResponse<ResponseSuccess<Nothing>, ResponseError>
    @GET("score/list")
    suspend fun score(@Query("id_schedule") id: Int): NetworkResponse<ResponseSuccess<ArrayList<Score>>, ResponseError>
    @GET("attendance/list")
    suspend fun attendance(@Query("id_schedule") id: Int): NetworkResponse<ResponseSuccess<ArrayList<Attendance>>, ResponseError>
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
    suspend fun thread(id: Int): NetworkResponse<ResponseSuccess<Thread>, ResponseError> {
        return userService.thread(id)
    }
    suspend fun threadCreate(thread: Thread.Create): NetworkResponse<ResponseSuccess<Thread.ThreadList>, ResponseError> {
        return userService.threadCreate(thread)
    }
    suspend fun threadDelete(thread: Thread.Delete): NetworkResponse<ResponseSuccess<Nothing>, ResponseError> {
        return userService.threadDelete(thread)
    }
    suspend fun score(id: Int): NetworkResponse<ResponseSuccess<ArrayList<Score>>, ResponseError> {
        return userService.score(id)
    }
    suspend fun attendance(id: Int): NetworkResponse<ResponseSuccess<ArrayList<Attendance>>, ResponseError> {
        return userService.attendance(id)
    }
}