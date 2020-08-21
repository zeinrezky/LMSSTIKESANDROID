package com.example.lmsstikes.view.base

import android.content.DialogInterface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lmsstikes.util.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val openDialog: MutableLiveData<HashMap<Int, DialogInterface.OnClickListener>> = MutableLiveData()
    val toastMessage : MutableLiveData<Any> = MutableLiveData()
    val snackbarMessage : MutableLiveData<Any> = MutableLiveData()
    val networkError : MutableLiveData<Any> = MutableLiveData()
    val showLoading : MutableLiveData<Boolean> = MutableLiveData()
    val hideKeyBoard: SingleLiveEvent<Unit> = SingleLiveEvent()
    val invalidToken: SingleLiveEvent<Unit> = SingleLiveEvent()
}