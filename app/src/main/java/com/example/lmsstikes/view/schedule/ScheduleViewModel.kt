package com.example.lmsstikes.view.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lmsstikes.model.*
import com.example.lmsstikes.repository.UserRepository
import com.example.lmsstikes.util.SingleLiveEvent
import com.example.lmsstikes.view.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class ScheduleViewModel(private val repository: UserRepository) : BaseViewModel() {

    /** ScheduleFragment */
    val period = MutableLiveData<String>()

    val clickPeriod = SingleLiveEvent<Unit>()

    val listSchedule = MutableLiveData<ArrayList<Schedule>>()
    val listCourse = MutableLiveData<ArrayList<Course>>()
    val listSession = MutableLiveData<ArrayList<Session>>()
    val listTopic = MutableLiveData<ArrayList<Topic>>()

    fun getListSchedule() {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.schedule()) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listSchedule.value = response.body.data!!
                }
                is NetworkResponse.ServerError -> {
                    isLoading.value = false
                    snackbarMessage.value = response.body?.message
                }
                is NetworkResponse.NetworkError -> {
                    isLoading.value = false
                    networkError.value = response.error.message.toString()
                }
            }
        }
    }

    fun getListCourse(id_schedule: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.course(id_schedule)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listCourse.value = response.body.data!!
                }
                is NetworkResponse.ServerError -> {
                    isLoading.value = false
                    snackbarMessage.value = response.body?.message
                }
                is NetworkResponse.NetworkError -> {
                    isLoading.value = false
                    networkError.value = response.error.message.toString()
                }
            }
        }
    }

    fun getListSession(id_course: Int, month: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.session(id_course, month)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listSession.value = response.body.data!!
                }
                is NetworkResponse.ServerError -> {
                    isLoading.value = false
                    snackbarMessage.value = response.body?.message
                }
                is NetworkResponse.NetworkError -> {
                    isLoading.value = false
                    networkError.value = response.error.message.toString()
                }
            }
        }
    }

    fun getListTopic(id_session: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.topic(id_session)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listTopic.value = response.body.data!!
                }
                is NetworkResponse.ServerError -> {
                    isLoading.value = false
                    snackbarMessage.value = response.body?.message
                }
                is NetworkResponse.NetworkError -> {
                    isLoading.value = false
                    networkError.value = response.error.message.toString()
                }
            }
        }
    }

    fun onClickPeriod() {
        clickPeriod.call()
    }

    /** Course */
    val courseName = MutableLiveData<String>()
    val courseCode = MutableLiveData<String>()
    val courseType = MutableLiveData<String>()
    val courseClass = MutableLiveData<String>()

}