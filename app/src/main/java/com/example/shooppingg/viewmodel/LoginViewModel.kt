package com.example.shooppingg.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _etEmail = MutableLiveData<String>().apply { value = "" }
    fun getEtEmail() = _etEmail
    fun setEtEmail(text : String) { _etEmail.value = text }

    private val _etPassword = MutableLiveData<String>().apply { value = "" }
    fun getEtPassword() = _etPassword
    fun setEtPassword(text : String) { _etPassword.value = text }
}