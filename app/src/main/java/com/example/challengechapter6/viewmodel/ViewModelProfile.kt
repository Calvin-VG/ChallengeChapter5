package com.example.challengechapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter6.model.UpdateUserResponse
import com.example.challengechapter6.model.UserDetails
import com.example.challengechapter6.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelProfile: ViewModel() {

    lateinit var liveDataDetailUser : MutableLiveData<List<UserDetails>>
    lateinit var liveDataUpdate : MutableLiveData<UpdateUserResponse>

    init {
        liveDataDetailUser = MutableLiveData()
        liveDataUpdate = MutableLiveData()
    }

    @JvmName("getLiveDataDetailUser1")
    fun getLiveDataDetailUser(): MutableLiveData<List<UserDetails>>{
        return liveDataDetailUser
    }
    @JvmName("getLiveDataUpdate1")
    fun getLiveDataUpdate(): MutableLiveData<UpdateUserResponse>{
        return liveDataUpdate
    }

    fun detailUserProfile(id: Int){
        ApiClient.instance.detailUser(id)
            .enqueue(object : Callback<List<UserDetails>>{
                override fun onResponse(
                    call: Call<List<UserDetails>>,
                    response: Response<List<UserDetails>>
                ) {
                    if (response.isSuccessful){
                        liveDataDetailUser.postValue(response.body())
                    }else{
                        liveDataDetailUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<UserDetails>>, t: Throwable) {
                    liveDataDetailUser.postValue(null)
                }

            })
    }

    fun updateUserProfile(id: Int, compname: String, uname: String, add: String, birth: String){
        ApiClient.instance.updateUser(id.toString(),compname,uname,add,birth)
            .enqueue(object : retrofit2.Callback<UpdateUserResponse>{
                override fun onResponse(
                    call: Call<UpdateUserResponse>,
                    response: Response<UpdateUserResponse>
                ) {
                    if (response.isSuccessful){
                        liveDataUpdate.postValue(response.body())
                    }else{
                        liveDataUpdate.postValue(null)
                    }
                }

                override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                    liveDataUpdate.postValue(null)
                }

            })
    }
}