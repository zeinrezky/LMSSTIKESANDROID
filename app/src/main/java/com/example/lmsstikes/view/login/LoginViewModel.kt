package com.example.lmsstikes.view.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch
import java.util.*

//class LoginViewModel(private val userRepository: UserRepository,
//                     private val deviceHelper: DeviceHelper) : BaseViewModel(){

//    /**
//     * Login
//     */
//
//    val email = MutableLiveData<String>()
//    val password = MutableLiveData<String>()
//    val isEmailErrorVisible = MutableLiveData<Boolean>().apply { value = false }
//    val isPasswordErrorVisible = MutableLiveData<Boolean>().apply { value = false }
//    val loginAccept = SingleLiveEvent<Unit>()
//    val openLoginWithPhone = SingleLiveEvent<Unit>()
//    val openSignUp = SingleLiveEvent<Unit>()
//    val emailError = MutableLiveData<Int>()
//
//    fun onValidateField() {
//        if (email.value.isNullOrEmpty().not()) {
//            isEmailErrorVisible.value = false
//        }
//        if (password.value.isNullOrEmpty().not()) {
//            isPasswordErrorVisible.value = false
//        }
//    }
//
//    fun onClickLogin() {
//        hideKeyBoard.call()
//        var valid = true
//        if (email.value.isNullOrEmpty()) {
//            isEmailErrorVisible.value = true
//            emailError.value = 0
//            valid = false
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
//            emailError.value = 1
//            isEmailErrorVisible.value = true
//            valid = false
//        }
//        if (password.value.isNullOrEmpty()) {
//            isPasswordErrorVisible.value = true
//            valid = false
//        }
//        if (valid) {
//            val loginRequest = setLoginRequest(email.value!!, password.value!!, Constant.Type.S.value)
//            login(loginRequest)
//        }
//    }
//
//    private fun setLoginRequest(email: String, password: String, loginType: String): Login {
//        val login = Login()
//        login.social_id = ""
//        login.email = email
//        login.password = password
//        login.cell_phone = deviceHelper.operatorPhone
//        login.login_type = loginType
//        login.device_type = "A" //Android
//        login.device_token = AppPreference.getDeviceToken()
//        login.uuid = UUID.randomUUID().toString()
//        login.os_version = deviceHelper.versionRelease
//        login.app_version = BuildConfig.VERSION_CODE
//        login.device_name = deviceHelper.deviceManufacture
//        login.model_name = deviceHelper.deviceModel
//        login.ip = deviceHelper.getIpAddress()
//        return login
//    }
//
//    private fun login(request: Login) {
//        isLoading.value = true
//        viewModelScope.launch {
//            when (val response = userRepository.login(request)) {
//                is NetworkResponse.Success -> {
//                    isLoading.value = false
//                    AppPreference.putLoginData(response.body.data)
//                    loginAccept.call()
//                }
//                is NetworkResponse.ServerError -> {
//                    isLoading.value = false
//                    snackbarMessage.value = response.body?.message
//                }
//                is NetworkResponse.NetworkError -> {
//                    isLoading.value = false
//                    networkError.value = response.error.message.toString()
//                }
//            }
//        }
//    }
//
//    fun onClickLoginWithPhone() {
//        openLoginWithPhone.call()
//    }
//
//    fun onClickSignUp() {
//        openSignUp.call()
//    }
//
//    /**
//     * Login Phone
//     */
//
//    val phone = MutableLiveData<String>()
//    val isPhoneErrorVisible = MutableLiveData<Boolean>().apply { value = false }
//    val sendOTPSuccess = SingleLiveEvent<Unit>()
//    val openLoginWithEmail = SingleLiveEvent<Unit>()
//
//    fun onValidatePhone() {
//        if (phone.value.isNullOrEmpty().not()) {
//            isPhoneErrorVisible.value = false
//        }
//    }
//
//    fun onClickSendOTP() {
//        hideKeyBoard.call()
//        var valid = true
//        if (phone.value.isNullOrEmpty() || phone.value!!.length < 10) {
//            isPhoneErrorVisible.value = true
//            valid = false
//        }
//        if (valid) {
//            var cellPhone = phone.value.toString()
//            if (cellPhone[0].toString() == "0") {
//                cellPhone = cellPhone.removePrefix("0")
//            }
//            AppPreference.putPhone(cellPhone)
//
//            val otp = SendOTP()
//            otp.cell_phone = cellPhone
//            otp.app_version = BuildConfig.VERSION_CODE
//            sendOTP(otp)
//        }
//    }
//
//    private fun sendOTP(request: SendOTP) {
//        isLoading.value = true
//        viewModelScope.launch {
//            when (val result = userRepository.sendOTP(request)) {
//                is NetworkResponse.Success -> {
//                    isLoading.value = false
//                    sendOTPSuccess.call()
//                }
//                is NetworkResponse.ServerError -> {
//                    isLoading.value = false
//                    snackbarMessage.value = result.body?.message
//                }
//                is NetworkResponse.NetworkError -> {
//                    isLoading.value = false
//                    networkError.value = result.error.message.toString()
//                }
//            }
//        }
//    }
//
//    fun onClickLoginWithEmail() {
//        openLoginWithEmail.call()
//    }
//}