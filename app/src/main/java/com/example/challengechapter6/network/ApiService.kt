package com.example.challengechapter6.network

import com.example.challengechapter6.model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("register.php")
    @FormUrlEncoded
    fun registerUser(
        @Field("email")email : String,
        @Field("password")password : String,
        @Field("username")username : String,
    ): Call<RegisterResponse>

    @POST("login.php")
    @FormUrlEncoded
    fun loginUser(
        @Field("email")email : String,
        @Field("password") password : String
    ): Call<List<UserResponse>>

    @GET("apiuser.php")
    fun getAllUser(): Call<List<UserResponse>>

    @GET("apifilm.php")
    fun getAllFilm(): Call<List<GetAllFilmResponseItem>>

    @POST("updateuser.php")
    @FormUrlEncoded
    fun updateUser(
        @Field("id")id : String,
        @Field("complete_name")completename : String,
        @Field("username")username : String,
        @Field("address")adress : String,
        @Field("dateofbirth")birthday : String
    ): Call<UpdateUserResponse>

    @POST("detailuser.php")
    @FormUrlEncoded
    fun detailUser(@Field("id") id : Int) : Call<List<UserDetails>>

}