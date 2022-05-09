package com.example.challengechapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter6.model.UserResponse
import com.example.challengechapter6.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ViewModelLogin: ViewModel() {

    lateinit var liveDataLogin : MutableLiveData<List<UserResponse>>

    init {
        liveDataLogin = MutableLiveData()
    }

    @JvmName("getLiveDataLogin1")
    fun getLiveDataLogin(): MutableLiveData<List<UserResponse>>{
        return liveDataLogin
    }

    fun loginUser(){
        ApiClient.instance.getAllUser()
            .enqueue(object : retrofit2.Callback<List<UserResponse>>{
                override fun onResponse(
                    call: Call<List<UserResponse>>,
                    response: Response<List<UserResponse>>
                ) {
                    if (response.isSuccessful){
                        liveDataLogin.postValue(response.body())
                    }else{
                        liveDataLogin.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    liveDataLogin.postValue(null)
                }

            })
    }

}