package com.example.lmsstikes.view.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lmsstikes.model.Dashboard
import com.example.lmsstikes.repository.UserRepository
import com.example.lmsstikes.util.SingleLiveEvent
import com.example.lmsstikes.view.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: UserRepository) : BaseViewModel() {

    /** DashboardFragment */
    val gpa = MutableLiveData<String>()
    val clickGpa = SingleLiveEvent<Unit>()

    val listAnnouncement = MutableLiveData<ArrayList<Dashboard.Announcement>>()
    val listKnowledge = MutableLiveData<ArrayList<Dashboard.Knowledge>>()
    val listWhatsOn = MutableLiveData<ArrayList<Dashboard.WhatsOn>>()
    val listCampusDir = MutableLiveData<ArrayList<Dashboard.CampusDir>>()
    val listAbout = MutableLiveData<ArrayList<Dashboard.About>>()

    fun getList() {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.home()) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listAnnouncement.value = response.body.data.announcement
                    listKnowledge.value = response.body.data.knowledge
                    listWhatsOn.value = response.body.data.whats_on
                    listCampusDir.value = response.body.data.campus_dir
                    listAbout.value = response.body.data.about
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

}
