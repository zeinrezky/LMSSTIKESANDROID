package com.example.lmsstikes.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lmsstikes.model.Login
import com.example.lmsstikes.repository.UserRepository
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.util.SingleLiveEvent
import com.example.lmsstikes.view.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : BaseViewModel(){

    /**
     * Login
     */

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginSuccess = SingleLiveEvent<Unit>()

    fun onClickLogin() {
        hideKeyBoard.call()
        var valid = true
        if (username.value.isNullOrEmpty()) {
            valid = false
        }
        if (password.value.isNullOrEmpty()) {
            valid = false
        }
        if (valid) {
            val loginRequest = setLoginRequest(username.value!!, password.value!!)
            login(loginRequest)
        }
    }

    private fun setLoginRequest(username: String, password: String): Login {
        val login = Login()
        login.username = username
        login.password = password
        return login
    }

    private fun login(request: Login) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = userRepository.login(request)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    if (response.body.code == 200){
                        AppPreference.putLoginData(response.body.data)
                        loginSuccess.call()
                    }
                    else
                        snackbarMessage.value = response.body.message


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
}