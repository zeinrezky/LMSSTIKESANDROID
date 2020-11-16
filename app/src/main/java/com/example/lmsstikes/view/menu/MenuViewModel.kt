package com.example.lmsstikes.view.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lmsstikes.model.*
import com.example.lmsstikes.repository.UserRepository
import com.example.lmsstikes.util.SingleLiveEvent
import com.example.lmsstikes.view.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class MenuViewModel(private val repository: UserRepository) : BaseViewModel() {

    /** Menu */
    val period = MutableLiveData<String>()

    val clickPeriod = SingleLiveEvent<Unit>()

    val listSchedule = MutableLiveData<ArrayList<Schedule>>()
    val listCourse = MutableLiveData<ArrayList<Course>>()
    val listExam = MutableLiveData<ArrayList<Exam>>()
    val listSession = MutableLiveData<ArrayList<Session>>()
    val listTopic = MutableLiveData<ArrayList<Topic>>()
    val listThread = MutableLiveData<ArrayList<Thread.ThreadList>>()
    val listScore = MutableLiveData<ArrayList<Score>>()
    val listAttendance = MutableLiveData<ArrayList<Attendance>>()

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

    fun getListExam(id_schedule: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.exam(id_schedule)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listExam.value = response.body.data!!
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

    fun getListSession(id_course: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.session(id_course)) {
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

    fun getListSessionSchedule(date: Int, month: Int, year: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.sessionSchedule(date, month, year)) {
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

    fun getListScheduleDetail(month: Int, year: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.scheduleDetail(month, year)) {
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

    fun getListThread(id_session: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.thread(id_session)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listThread.value = response.body.data.data
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

    fun createThread(thread: Thread.Create) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.threadCreate(thread)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    threadData.value = response.body.data!!
                    getListThread(response.body.data.id_session)
                    responseSuccess.call()
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

    fun deleteThread(thread: Thread.Delete) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.threadDelete(thread)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    snackbarMessage.value = response.body.message
                    responseSuccess.call()
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

    fun updateThread(thread: Thread.Update) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.threadUpdate(thread)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    snackbarMessage.value = response.body.message
                    responseSuccess.call()
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

    fun getListScore(id_schedule: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.score(id_schedule)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listScore.value = response.body.data!!
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

    fun getListAttendance(id_schedule: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.attendance(id_schedule)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listAttendance.value = response.body.data!!
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

    val sessionName = MutableLiveData<String>()

    val clickSchedule = SingleLiveEvent<Unit>()
    val clickScheduleDetail = SingleLiveEvent<Unit>()

    val clickCourse = SingleLiveEvent<Unit>()
    val clickForum = SingleLiveEvent<Unit>()
    val clickAttendance = SingleLiveEvent<Unit>()
    val clickScore = SingleLiveEvent<Unit>()
    val clickLogout = SingleLiveEvent<Unit>()


    fun onClickSchedule() {
        clickSchedule.call()
    }
    fun onClickScheduleDetail() {
        clickScheduleDetail.call()
    }
    fun onClickCourse() {
        clickCourse.call()
    }
    fun onClickForum() {
        clickForum.call()
    }
    fun onClickAttendance() {
        clickAttendance.call()
    }
    fun onClickScore() {
        clickScore.call()
    }
    fun onClickLogout() {
        clickLogout.call()
    }

    /** Thread */
    val userName = MutableLiveData<String>()
    val userType = MutableLiveData<String>()
    val datePost = MutableLiveData<String>()
    val threadTitle = MutableLiveData<String>()
    val threadContent = MutableLiveData<String>()
    val clickQuote = SingleLiveEvent<Unit>()
    val clickReply = SingleLiveEvent<Unit>()
    val clickCreateThread = SingleLiveEvent<Unit>()
    val clickSend = SingleLiveEvent<Unit>()
    val clickRemove = SingleLiveEvent<Unit>()
    val clickLock = SingleLiveEvent<Unit>()
    val clickClose = SingleLiveEvent<Unit>()
    val responseSuccess = SingleLiveEvent<Unit>()
    val threadData = MutableLiveData<Thread.ThreadList>()

    fun onClickQuote() {
        clickQuote.call()
    }
    fun onClickReply() {
        clickReply.call()
    }
    fun onClickCreateThread(){
        clickCreateThread.call()
    }
    fun onClickSend(){
        clickSend.call()
    }
    fun onClickRemove() {
        clickRemove.call()
    }
    fun onClickLock() {
        clickLock.call()
    }
    fun onClickClose() {
        clickClose.call()
    }
    val clickInfo = SingleLiveEvent<Unit>()
    val date = MutableLiveData<String>()

    fun onClickInfo() {
        clickInfo.call()
    }
}