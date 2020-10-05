package com.example.lmsstikes.view.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lmsstikes.model.Dashboard
import com.example.lmsstikes.repository.UserRepository
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.util.SingleLiveEvent
import com.example.lmsstikes.view.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: UserRepository) : BaseViewModel() {

    /** DashboardFragment */
    val gpa = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val img = MutableLiveData<String>()

    val clickViewAllKnowledge = SingleLiveEvent<Unit>()
    val clickViewAllWhatsOn = SingleLiveEvent<Unit>()
    val clickGpa = SingleLiveEvent<Unit>()
    val clickLogout = SingleLiveEvent<Unit>()

    val listAnnouncement = MutableLiveData<ArrayList<Dashboard.Announcement>>()
    val listKnowledge = MutableLiveData<ArrayList<Dashboard.Knowledge>>()
    val listWhatsOn = MutableLiveData<ArrayList<Dashboard.WhatsOn>>()
    val listCampusDir = MutableLiveData<ArrayList<Dashboard.CampusDir>>()
    val listAbout = MutableLiveData<ArrayList<Dashboard.About>>()

    fun getList() {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.home(2, 2, 2, 2)) {
                is NetworkResponse.Success -> {
                    if (response.body.code == 200){
                        isLoading.value = false
                        listAnnouncement.value = response.body.data.announcement
                        listKnowledge.value = response.body.data.knowledge
                        listWhatsOn.value = response.body.data.whats_on
                        listCampusDir.value = response.body.data.campus_dir
                        listAbout.value = response.body.data.about
                        id.value = response.body.data.profile.user_code
                        name.value = response.body.data.profile.name
                        gpa.value = response.body.data.profile.gpa
                        img.value = response.body.data.profile.img
                    }
                    else {
                        snackbarMessage.value = response.body.message
                        clickLogout.call()
                    }

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
    fun getListKnowledge() {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.knowledge()) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listKnowledge.value = response.body.data.data
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
    fun getListWhatsOn() {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.whatsOn()) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listWhatsOn.value = response.body.data.data
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

    fun onClickGpa() {
        clickGpa.call()
    }
    fun onClickViewAllKnowledge() {
        clickViewAllKnowledge.call()
    }
    fun onClickViewAllWhatsOn() {
        clickViewAllWhatsOn.call()
    }

    /** DetailFragment */
    val title = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val image = MutableLiveData<String>()

}
