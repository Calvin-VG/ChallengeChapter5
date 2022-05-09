package com.example.challengechapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter6.model.GetAllFilmResponseItem
import com.example.challengechapter6.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<List<GetAllFilmResponseItem>>

    init {
        liveDataFilm = MutableLiveData()
    }

    @JvmName("getLiveDataFilm1")
    fun getLiveDataFilm(): MutableLiveData<List<GetAllFilmResponseItem>>{
        return liveDataFilm
    }

    fun getDataFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<GetAllFilmResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetAllFilmResponseItem>>,
                    response: Response<List<GetAllFilmResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    }else{
                        liveDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetAllFilmResponseItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })
    }

}