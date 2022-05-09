package com.example.challengechapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter6.model.RegisterResponse
import com.example.challengechapter6.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelRegister : ViewModel() {

    lateinit var liveDataRegister : MutableLiveData<RegisterResponse>

    init {
        liveDataRegister = MutableLiveData()
    }

    @JvmName("getLiveDataRegister1")
    fun getLiveDataRegister() : MutableLiveData<RegisterResponse>{
        return liveDataRegister
    }

    fun registerDataUser(email: String, passwd: String, uname: String){
        ApiClient.instance.registerUser(email, passwd, uname)
            .enqueue(object : Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful){
                        liveDataRegister.postValue(response.body())
                    }else{
                        liveDataRegister.postValue(null)
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    liveDataRegister.postValue(null)
                }

            })
    }

}