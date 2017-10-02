package com.noonight.pc.schedule.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestApi : Api {

    private val brioApi: BriopApi

    init {
        //Log.d("init Schedule Rest API")
        val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2")
                //.baseUrl("http://192.168.1.64")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        brioApi = retrofit.create(BriopApi::class.java)
        //Log.d("completed briopApi -> $brioApi")
    }

    override fun getLessons(): Call<List<Lessons>> {
        return brioApi.getLessons()
    }

    override fun getUsers_Type(): Call<List<Users_Type>> {
        return brioApi.getUsers_type()
    }

    override fun getUsers(): Call<List<Users>> {
        return brioApi.getUsers()
    }

    override fun getCourses(): Call<List<Courses>> {
        return brioApi.getCourses()
    }

    override fun getListeners(): Call<List<Listeners>> {
        return brioApi.getListeners()
    }
}